import java.security.MessageDigest
import java.net.URI
import java.io.InputStream
import java.nio.file.FileSystems
import java.nio.file.Files

abstract class DownloadJarsTask : DefaultTask() {
	@get:OutputDirectory
	abstract val outputDir: DirectoryProperty

	@get:Input
	abstract val jars: MapProperty<String, String>

	@TaskAction
	fun download() {
		val jarDir = outputDir.get().asFile
		if (!jarDir.exists()) jarDir.mkdirs()

		jars.get().forEach { (name, url) ->
			val dest = File(jarDir, name)
			if (!dest.exists()) {
				println("Downloading $name...")
				URI.create(url).toURL().openStream().use { input ->
					dest.outputStream().use { output ->
						(input as InputStream).copyTo(output)
					}
				}

				// fill-data URLs are content-addressed: .../objects/<sha256>/<file>
				val expected = url.substringBeforeLast('/').substringAfterLast('/')
				if (Regex("[0-9a-f]{64}").matches(expected)) {
					val digest = MessageDigest.getInstance("SHA-256")
					dest.inputStream().use { ins ->
						val buf = ByteArray(8192)
						var n = ins.read(buf)
						while (n >= 0) { digest.update(buf, 0, n); n = ins.read(buf) }
					}
					val actual = digest.digest().joinToString("") { "%02x".format(it) }
					if (actual != expected) {
						dest.delete()
						throw GradleException("Checksum mismatch for $name: expected $expected but was $actual")
					}
				}

				// Strip conflicting Adventure BossBar service provider from jars (like Velocity)
				val env = mapOf("create" to "false")
				val jarUri = URI.create("jar:file:${dest.absolutePath}")
				try {
					FileSystems.newFileSystem(jarUri, env).use { fs ->
						val providerPath = fs.getPath("META-INF/services/net.kyori.adventure.bossbar.BossBarImplementation\$Provider")
						if (Files.exists(providerPath)) {
							println("Stripping conflicting BossBar provider from $name...")
							Files.delete(providerPath)
						}
					}
				} catch (e: Exception) {
					// Ignore if not a valid jar or if it fails
				}
			}
		}
	}
}

plugins {
	id("checkstyle")
	id("java-library")
	id("jacoco")
	id("com.vanniktech.maven.publish") version "0.36.0"
	id("net.kyori.blossom") version "2.2.0"
	id("com.diffplug.spotless") version "7.0.2"
	id("signing")
	id("org.sonarqube") version "6.0.1.5171"
	id("eclipse")
	id("idea")
}

group = "io.github.secondlifegaming"
version = getFullVersion()

// versions.json is the single source of truth for which artifacts are used.
// Every listed version is downloaded (platforms only list versions that exist upstream);
// per platform, the entry flagged "primary" (otherwise the first listed) drives the backend test classpath.
val manifestResolved: Pair<List<String>, Map<String, String>> = run {
	val manifestFile = file("jars/manifests/versions.json")
	check(manifestFile.exists()) { "Missing version manifest: $manifestFile" }
	@Suppress("UNCHECKED_CAST")
	val root = groovy.json.JsonSlurper().parse(manifestFile) as Map<String, Any?>
	@Suppress("UNCHECKED_CAST")
	val platforms = root["platforms"] as Map<String, Map<String, Any?>>
	val all = mutableListOf<String>()
	val selected = linkedMapOf<String, String>()
	platforms.forEach { (platform, def) ->
		@Suppress("UNCHECKED_CAST")
		val versions = def["versions"] as List<Map<String, Any?>>
		check(versions.isNotEmpty()) { "Manifest platform '$platform' has no versions" }
		versions.forEach { v ->
			all += v["url"] as? String ?: error("Manifest platform '$platform' entry lacks a url")
		}
		val chosen = versions.firstOrNull { it["primary"] == true } ?: versions.first()
		selected[platform] = chosen["url"] as String
	}
	check(all.map { it.substringAfterLast('/') }.toSet().size == all.size) { "Duplicate artifact names in version manifest" }
	all.toList() to selected.toMap()
}
val manifestAllUrls: List<String> = manifestResolved.first
val manifestSelection: Map<String, String> = manifestResolved.second

tasks.register<DownloadJarsTask>("downloadJars") {
	group = "setup"
	description = "Downloads all backend API JARs listed in jars/manifests/versions.json"
	outputDir.set(layout.projectDirectory.dir("jars"))
	jars.set(manifestAllUrls.associateBy { it.substringAfterLast('/') })
}

tasks.named("compileJava") {
	dependsOn("downloadJars")
}

tasks.withType<JavaCompile>().configureEach {
	options.isFork = true
	options.forkOptions.jvmArgs = mutableListOf("-Xint", "-Xshare:off")
}

sonar {
	properties {
		property("sonar.projectKey", "SecondLifeGaming_MockMC")
		property("sonar.organization", "secondlifegaming")
		property("sonar.host.url", "https://sonarcloud.io")
		property("sonar.coverage.jacoco.xmlReportPaths", "**/build/reports/jacoco/test/jacocoTestReport.xml")
		property("sonar.exclusions", "**/generated/**/*,src/main/java/org/mockmc/mockmc/generated/**/*,src/test/java/org/mockmc/mockmc/generated/**/*")
		property("sonar.test.exclusions", "**/generated/**/*,src/test/java/org/mockmc/mockmc/generated/**/*")
		property("sonar.coverage.exclusions", "**/generated/**/*,src/main/java/org/mockmc/mockmc/generated/**/*,src/test/java/org/mockmc/mockmc/generated/**/*")
		property("sonar.cpd.exclusions", "**/generated/**/*,src/main/java/org/mockmc/mockmc/generated/**/*,src/test/java/org/mockmc/mockmc/generated/**/*")
	}
}

checkstyle {
	toolVersion = "14.3.0"
}

tasks.withType<Checkstyle> {
	exclude("**/generated/**")
	exclude("io/papermc/**")
}

tasks.withType<Test>().configureEach {
	jvmArgs("-XX:-TieredCompilation", "-XX:+UseG1GC", "-Xmx1g")
}

eclipse {
	jdt {
		file {
			withProperties {
				setProperty("org.eclipse.jdt.core.compiler.problem.deprecation", "ignore")
				setProperty("org.eclipse.jdt.core.compiler.problem.deprecationInDeprecatedCode", "ignore")
				setProperty("org.eclipse.jdt.core.compiler.problem.deprecationWhenOverridingDeprecatedMethod", "ignore")
				setProperty("org.eclipse.jdt.core.compiler.problem.terminalDeprecation", "ignore")
				setProperty("org.eclipse.jdt.core.compiler.problem.varargsArgumentNeedCast", "ignore")
			}
		}
	}
}

spotless {
	java {
		target("src/**/*.java")
		eclipse().configFile(rootProject.file("config/eclipse-formatter.xml"))
		removeUnusedImports()
		trimTrailingWhitespace()
		endWithNewline()
	}
}

repositories {
	mavenCentral()
	maven("https://hub.spigotmc.org/nexus/content/repositories/public/")
	maven("https://repo.md-5.net/content/groups/public/")
	maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
	// Paper API - compileOnly + testImplementation to avoid snapshot issues in Maven Central
	compileOnly("io.papermc.paper:paper-api:${property("paper.api.full-version")}")
	testImplementation("io.papermc.paper:paper-api:${property("paper.api.full-version")}")

	// Remapped Server Jars from MetaMiner (Autonomous) - Paper primary, Folia secondary
	val remappedJars = files(
		fileTree("jars/cache") { include("remapped-paper-*.jar") },
		fileTree("jars/cache") { include("remapped-folia-*.jar") }
	)
	compileOnly(remappedJars)
	testCompileOnly(remappedJars)

	// Unbundled Libraries (Autonomous)
	compileOnly(fileTree("jars/cache/libraries") {
		include("**/*.jar")
		exclude("**/paper-api-*.jar")
		exclude("**/folia-api-*.jar")
	})
	testCompileOnly(fileTree("jars/cache/libraries") {
		include("**/*.jar")
		exclude("**/paper-api-*.jar")
		exclude("**/folia-api-*.jar")
	})

	// Fallback API: declared AFTER paper-api so Paper wins for shared classes.
	// Only classes missing from Paper (e.g. removed in a newer release) resolve from here,
	// which keeps mocks for older Folia APIs compiling.
	compileOnly(fileTree("jars/cache/libraries/dev/folia/folia-api") { include("**/*.jar") })
	testCompileOnly(fileTree("jars/cache/libraries/dev/folia/folia-api") { include("**/*.jar") })

	// Fallback Paper API: Sanitized by BaseMockGenerator so only classes removed in newer Paper remain.
	compileOnly(fileTree("jars/cache/libraries/io/papermc/paper/paper-api") {
		include("**/*.jar")
		exclude("**/*${property("paper.api.full-version")}*")
	})
	testCompileOnly(fileTree("jars/cache/libraries/io/papermc/paper/paper-api") {
		include("**/*.jar")
		exclude("**/*${property("paper.api.full-version")}*")
	})

	// Backend Jars for non-bundled or fallback resolution (Velocity, Bungee, etc.)
	val backendJars = fileTree("jars") {
		include("*.jar")
		exclude("paper-*.jar")
		exclude("folia-*.jar")
	}
	compileOnly(backendJars)
	testImplementation(backendJars)

	api("org.jetbrains:annotations:26.1.0")
	api("org.hamcrest:hamcrest:3.0")
	api("com.googlecode.json-simple:json-simple:1.1.1")
	api("org.checkerframework:checker-qual:3.48.3")
	api("it.unimi.dsi:fastutil:8.5.15")

	// Dependencies for Unit Tests
	implementation("org.junit.jupiter:junit-jupiter-api:6.0.3")
	testImplementation(platform("org.junit:junit-bom:6.0.3"))
	testImplementation("org.junit.jupiter:junit-jupiter")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	testImplementation("org.skyscreamer:jsonassert:1.5.3")
	testImplementation("com.mojang:brigadier:1.3.10")

	// General utilities for the project
	implementation("net.kyori:adventure-platform-bungeecord:4.4.1") {
		exclude("net.kyori", "adventure-platform-api")
		exclude("net.kyori", "adventure-api")
	}

	implementation("net.bytebuddy:byte-buddy:1.18.8")

	compileOnly("org.projectlombok:lombok:1.18.44")
	annotationProcessor("org.projectlombok:lombok:1.18.44")
	testCompileOnly("org.projectlombok:lombok:1.18.44")
	testAnnotationProcessor("org.projectlombok:lombok:1.18.44")

	// LibraryLoader dependencies
	implementation("org.apache.maven:maven-resolver-provider:3.8.5")
	implementation("org.apache.maven.resolver:maven-resolver-connector-basic:1.9.18")
	implementation("org.apache.maven.resolver:maven-resolver-transport-http:1.9.18")
}

tasks {
	processResources {
		val props = mapOf(
			"mockMCVersion" to getFullVersion(),
			"paperApiFullVersion" to project.property("paper.api.full-version").toString(),
			"paperApiVersion" to project.property("paper.api.version").toString(),
			"buildTime" to System.currentTimeMillis().toString(),
			"branch" to run("git", "rev-parse", "--abbrev-ref", "HEAD"),
			"commit" to run("git", "rev-parse", "HEAD"),
			"buildNumber" to (System.getenv("GITHUB_RUN_NUMBER") ?: "")
		)
		inputs.properties(props)
		filesMatching("mockmc-build.properties") {
			expand(props)
		}
	}
	jar {
		manifest {
			attributes(
				"Automatic-Module-Name" to "org.mockmc.mockmc",
				"Paper-Version" to project.property("paper.api.full-version").toString(),
				"Paper-API-Version" to project.property("paper.api.version").toString(),
				"MockMC-Version" to getFullVersion()
			)
		}
	}

	java {
		withSourcesJar()
	}

	compileJava {
		options.compilerArgs.addAll(listOf("-Xlint:deprecation", "-Xlint:removal", "-Xlint:unchecked", "-Xmaxwarns", "1000"))
		options.isFork = true
		// -XX:+UseSerialGC: workaround for JDK 25 javac NPE in checkDefaultMethodClashes.
		// The generated *BaseMock interface diamond hierarchy causes WeakHashMap keys inside
		// Types$CandidatesCache to be collected by a concurrent GC phase while get() is in
		// progress, producing a NullPointerException in Reference.refersTo(). SerialGC is
		// single-threaded so it cannot race with WeakHashMap lookups.
		// Remove once the upstream JDK 25 bug is fixed in a patch release.
		options.forkOptions.jvmArgs = listOf(
			"-Xmx8g",
			"-Xss4m",
			"-XX:MaxMetaspaceSize=1G",
			"-XX:+UseG1GC"
		)
	}

	compileTestJava {
		options.compilerArgs.addAll(listOf("-Xlint:deprecation", "-Xlint:removal", "-Xlint:unchecked", "-Xmaxwarns", "1000"))
		options.isFork = true
		options.forkOptions.memoryMaximumSize = "4g"
		options.forkOptions.jvmArgs = mutableListOf("-XX:-TieredCompilation", "-XX:+UseG1GC")
	}

	javadoc {
		options {
			(options as? StandardJavadocDocletOptions)?.apply {
				encoding = "UTF-8"
				tags(
					"apiNote:a:API Note:",
					"implSpec:a:Implementation Requirements:",
					"implNote:a:Implementation Note:",
					"mockmc.version:a:MockMC Version:"
				)
				// Custom options
				addBooleanOption("Xdoclint:all,-missing", true)
				jFlags = listOf(
					"-XX:-TieredCompilation",
					"-XX:+UseSerialGC"
				)
			}
		}
	}

	test {
		dependsOn(project(":extra:TestPlugin").tasks.jar)
		useJUnitPlatform()
		exclude("org/mockmc/mockmc/generated/**")
		ignoreFailures = true
		maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).coerceIn(1, 4)
		systemProperty("junit.jupiter.execution.parallel.enabled", "false")
		doFirst {
			println("Default test Classpath:")
			classpath.files.forEach { println("  - ${it.absolutePath}") }
		}
	}

	check {
		dependsOn(jacocoTestReport)
	}

	jacocoTestReport {
		dependsOn(test)
		dependsOn("compileJava", "processResources")
		reports {
			xml.required.set(true)
			html.required.set(true)
			html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
		}
		classDirectories.setFrom(
			sourceSets.main.get().output.classesDirs.asFileTree.matching {
				exclude("org/mockmc/mockmc/generated/**")
			}
		)
	}

	jacoco {
		toolVersion = "0.8.14"
	}

	register("updateResources") {
		dependsOn(":metaminer:runServer")
		doLast {
			copy {
				from("./metaminer/run/plugins/MetaMiner")
				into(".")
			}
		}
	}

	register("printPaperDetails")
	{
		notCompatibleWithConfigurationCache("Requires Project instance at execution time")
		doLast {
			val dependency = getDependencyInformation(
				dependencyGroup = "io.papermc.paper",
				artifactName = "paper-api",
				artifactVersion = project.property("paper.api.full-version").toString()
			)
			println("Paper version: ${project.property("paper.api.version")}")
			println("Paper full version: ${project.property("paper.api.full-version")}")
			println("Paper resolved version: ${dependency?.resolvedVersion}")
			println("Paper SHA-512: ${dependency?.hash}")
		}
	}
}

sourceSets {
	main {
		resources {
			srcDir("src/main/resources-autogenerated")
		}
	}
	test {
		resources {
			srcDir("src/test/resources-autogenerated")
		}
	}
}

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(property("java.version").toString().toInt()))
	}
}

mavenPublishing {
	coordinates(project.group.toString(), "mockmc-v${property("paper.api.version")}", project.version.toString())

	pom {
		description.set("MockMC is a mocking framework for bukkit to allow the easy unit testing of Bukkit plugins.")
		name.set("MockMC-v${property("paper.api.version")}")
		url.set("https://github.com/SecondLifeGaming/MockMC")
		licenses {
			license {
				name.set("MIT License")
				url.set("https://github.com/SecondLifeGaming/MockMC/blob/v${property("paper.api.version")}/LICENSE")
			}
		}
		developers {
			developer {
				id.set("westkevin12")
				name.set("westkevin12")
				email.set("west@digitalserverhost.com")
				url.set("https://github.com/SecondLifeGaming")
			}
		}
		scm {
			connection.set("scm:git:git://github.com/SecondLifeGaming/MockMC.git")
			developerConnection.set("scm:git:ssh://github.com:SecondLifeGaming/MockMC.git")
			url.set("https://github.com/SecondLifeGaming/MockMC/tree/v${property("paper.api.version")}")
		}
	}
	publishToMavenCentral(true)
	// No key available to sign with for maven local or GitHub Packages
	if (!project.gradle.startParameter.taskNames.any { it.contains("publishToMavenLocal") || it.contains("GitHubPackages") }) {
		signAllPublications()
	}
}

publishing {
	repositories {
		maven {
			name = "GitHubPackages"
			url = uri("https://maven.pkg.github.com/SecondLifeGaming/MockMC")
			credentials {
				username = System.getenv("GITHUB_ACTOR")
				password = System.getenv("GITHUB_TOKEN")
			}
		}
	}
}

signing {
	val inMemoryKey = project.findProperty("signingInMemoryKey") as? String
	if (!inMemoryKey.isNullOrEmpty()) {
		useInMemoryPgpKeys(
			inMemoryKey,
			project.findProperty("signingInMemoryKeyPassword") as? String
		)
	} else {
		useGpgCmd()
	}
}

fun isAction(): Boolean {
	return System.getenv("GITHUB_ACTIONS") == "true" && System.getenv("GITHUB_REPOSITORY") == "SecondLifeGaming/MockMC"
}

fun getFullVersion(): String {
	return if (isAction()) {
		property("mockmc.version") as String
	} else {
		"dev-${run("git", "rev-parse", "--verify", "--short", "HEAD")}"
	}
}

fun run(vararg cmd: String): String {
	return providers.exec {
		commandLine(*cmd)
	}.standardOutput.asText.get().trim()
}

/**
 * Computes the cryptographic hash of a resolved dependency artifact within a specified Gradle configuration.
 *
 * This method locates the target dependency in the resolved artifacts of the given configuration,
 * then streams the artifact file to compute its hash using the specified algorithm.
 *
 * @param dependencyGroup 		The group ID of the dependency (e.g., "org.jetbrains.kotlin").
 * @param artifactName 			The name of the dependency artifact (e.g., "kotlin-stdlib").
 * @param artifactVersion 		The version of the dependency (e.g., "1.9.0").
 * @param hashAlgorithm 		The hashing algorithm to use (default: "SHA-512").
 *                      		Common values include "SHA-256", "SHA-1", and "MD5".
 * @param targetConfiguration 	The name of the Gradle configuration to resolve from (default: "compileClasspath").
 *
 * @return The hexadecimal string representation of the computed hash, or `null` if the artifact
 *         could not be found or the hash computation failed.
 */
fun Project.getDependencyInformation(
	dependencyGroup: String,
	artifactName: String,
	artifactVersion: String,
	hashAlgorithm: String = "SHA-512",
	targetConfiguration: String = "compileClasspath"
): DependencyHashResult? {
	val artifact = try {
		configurations.getByName(targetConfiguration)
			.resolvedConfiguration
			.resolvedArtifacts
			.firstOrNull {
				val id = it.moduleVersion.id
				id.group == dependencyGroup &&
						id.name == artifactName &&
						id.version == artifactVersion
			} ?: run {
			logger.warn("Artifact not found: $dependencyGroup:$artifactName:$artifactVersion in configuration '$targetConfiguration'")
			return null
		}
	} catch (e: Exception) {
		logger.warn("Failed to resolve configuration '$targetConfiguration': ${e.message}")
		return null
	}

	val resolvedVersion = artifact.id.componentIdentifier.displayName
	val hash = try {
		val digest = MessageDigest.getInstance(hashAlgorithm)
		artifact.file.inputStream().buffered(DEFAULT_BUFFER_SIZE).use { input ->
			val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
			while (true) {
				val bytesRead = input.read(buffer)
				if (bytesRead == -1) {
					break
				}
				digest.update(buffer, 0, bytesRead)
			}
		}
		digest.digest().joinToString("") { "%02x".format(it) }
	} catch (e: Exception) {
		logger.error("Failed to compute digest for ${artifact.file.name}: ${e.message}")
		return null
	}

	return DependencyHashResult(hash = hash, resolvedVersion = resolvedVersion)
}

data class DependencyHashResult(
	val hash: String,
	val resolvedVersion: String
)

object JarServiceStripper {
	fun strip(jarFile: File) {
		if (!jarFile.exists()) return
		val servicesToStrip = listOf(
			"io.papermc.paper.ServerBuildInfo",
			"io.papermc.paper.InternalAPIBridge",
			"io.papermc.paper.datacomponent.item.ItemComponentTypesBridge",
			"io.papermc.paper.registry.RegistryAccess",
			"io.papermc.paper.plugin.lifecycle.event.types.LifecycleEventTypeProvider",
			"io.papermc.paper.datacomponent.item.attribute.AttributeModifierDisplayBridge",
			"io.papermc.paper.datacomponent.item.blocksattacks.BlocksAttacksBridge",
			"io.papermc.paper.command.brigadier.argument.VanillaArgumentProvider",
			"io.papermc.paper.command.brigadier.MessageComponentSerializer",
			"net.kyori.adventure.bossbar.BossBarImplementation\$Provider",
			"net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer\$Provider",
			"net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer\$Provider",
			"org.bukkit.plugin.PluginLoader"
		)
		val env = mapOf("create" to "false")
		val jarUri = URI.create("jar:file:${jarFile.absolutePath}")
		try {
			FileSystems.newFileSystem(jarUri, env).use { fs ->
				servicesToStrip.forEach { serviceName ->
					val providerPath = fs.getPath("META-INF/services/$serviceName")
					if (Files.exists(providerPath)) {
						println("Stripping conflicting service provider $serviceName from ${jarFile.name}...")
						Files.delete(providerPath)
					}
				}
			}
		} catch (e: Exception) {
			println("Failed to strip service providers from ${jarFile.name}: ${e.message}")
		}
	}
}

val backendJars: Map<String, String> = manifestSelection.entries.associate { (platform, url) ->
	platform.replaceFirstChar { it.uppercase() } to url.substringAfterLast('/')
}

backendJars.forEach { (name, jarName) ->
	tasks.register<Test>("test$name") {
		group = "verification"
		description = "Runs tests against $name backend"
		dependsOn(project(":extra:TestPlugin").tasks.jar)
		useJUnitPlatform()
		maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).coerceIn(1, 4)
		systemProperty("junit.jupiter.execution.parallel.enabled", "false")
		jvmArgs("-Xint", "-Xshare:off")
		// Backend verification must fail the build on genuine test failures.
		ignoreFailures = false

		if (name == "Velocity") {
			include("org/mockmc/mockmc/generated/proxy/com/velocitypowered/**")
		} else if (name == "Waterfall") {
			include("org/mockmc/mockmc/generated/server/net/md_5/bungee/**")
		} else if (name == "Paper") {
			include("org/mockmc/mockmc/**")
			exclude("org/mockmc/mockmc/generated/proxy/**")
			exclude("org/mockmc/mockmc/generated/server/net/md_5/bungee/**")
			// Folia-only classes absent from Paper 26.3
			exclude("org/mockmc/mockmc/generated/server/org/bukkit/block/data/type/PinkPetalsBaseMockTest*")
			exclude("org/mockmc/mockmc/generated/server/io/papermc/paper/datacomponent/item/MapItemColor*")
		} else { // Folia
			include("org/mockmc/mockmc/**")
			exclude("org/mockmc/mockmc/generated/proxy/**")
			exclude("org/mockmc/mockmc/generated/server/net/md_5/bungee/**")
		}

		val remappedJar = file("jars/cache/remapped-$jarName")
		val backendFile = if (remappedJar.exists()) remappedJar else file("jars/$jarName")
		val libraries = fileTree("jars/cache/libraries") {
			include("**/*.jar")
			exclude("**/paper-api-*.jar")
			exclude("**/folia-api-*.jar")
		}

		val foliaApi = fileTree("jars/cache/libraries/dev/folia/folia-api") { include("**/*.jar") }

		// Folia runs against its own API first so classes only present in that API resolve at runtime.
		classpath = if (name == "Folia") {
			foliaApi + sourceSets.test.get().runtimeClasspath + files(backendFile) + libraries
		} else {
			sourceSets.test.get().runtimeClasspath + files(backendFile) + libraries
		}
		testClassesDirs = sourceSets.test.get().output.classesDirs

		doFirst {
			println("Running tests against backend: $name ($jarName)")
			JarServiceStripper.strip(backendFile)
			println("Test Classpath:")
			classpath.files.forEach { println("  - ${it.absolutePath}") }
		}
	}
}

tasks.register("testAllBackends") {
	group = "verification"
	description = "Runs the test suite against all provided backend JARs"
	dependsOn("testFolia", "testPaper", "testVelocity", "testWaterfall")
}

configurations.all {
	resolutionStrategy {
		force("com.mojang:brigadier:1.3.10")
	}
}





