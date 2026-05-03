import java.security.MessageDigest
import java.net.URI
import java.io.InputStream

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
}

group = "io.github.secondlifegaming"
version = getFullVersion()

tasks.register<DownloadJarsTask>("downloadJars") {
	group = "setup"
	description = "Downloads backend API JARs for metaminer"
	outputDir.set(layout.projectDirectory.dir("jars"))
	jars.set(mapOf(
		"velocity-3.5.0-SNAPSHOT-593.jar" to "https://fill-data.papermc.io/v1/objects/25bfbee6155fbce24f709bf18f1bb915817c4151d6d418ca01282742ab1f123a/velocity-3.5.0-SNAPSHOT-593.jar",
		"paper-26.1.2-53.jar" to "https://fill-data.papermc.io/v1/objects/6934188878fc351e1be5bfba5f2b8c4591224886e4b34e3de09dbec68a351caf/paper-26.1.2-53.jar",
		"folia-1.21.11-14.jar" to "https://fill-data.papermc.io/v1/objects/f52c408490a0225611e67907a3ca19f7e6da2c6bc899e715d5f46844e7103c39/folia-1.21.11-14.jar",
		"waterfall-1.21-609.jar" to "https://fill-data.papermc.io/v1/objects/5439f3875772e1810284e5f37886cfea8bf48ef6c665e214f30d1146ad66af70/waterfall-1.21-609.jar"
	))
}

tasks.named("compileJava") {
	dependsOn("downloadJars")
}

sonar {
	properties {
		property("sonar.projectKey", "SecondLifeGaming_MockMC")
		property("sonar.organization", "secondlifegaming")
		property("sonar.host.url", "https://sonarcloud.io")
		property("sonar.coverage.jacoco.xmlReportPaths", "**/build/reports/jacoco/test/jacocoTestReport.xml")
		property("sonar.exclusions", "**/generated/**,src/main/java/org/mockmc/mockmc/generated/**")
		property("sonar.coverage.exclusions", "**/generated/**,src/main/java/org/mockmc/mockmc/generated/**")
		property("sonar.cpd.exclusions", "**/generated/**,src/main/java/org/mockmc/mockmc/generated/**")
	}
}

checkstyle {
	toolVersion = "13.4.1"
}

tasks.withType<Checkstyle> {
	exclude("**/generated/**")
	exclude("io/papermc/**")
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

	// Remapped Server Jars from MetaMiner (Autonomous)
	compileOnly(fileTree("jars/cache") { include("remapped-*.jar") })

	// Unbundled Libraries (Autonomous)
	compileOnly(fileTree("jars/cache/libraries") { 
		include("**/*.jar") 
		exclude("**/folia-api-*.jar")
		exclude("**/paper-api-*.jar")
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
		options.forkOptions.jvmArgs = listOf(
			"-Xmx8g",
			"-Xss4m",
			"-XX:MaxMetaspaceSize=1G",
			"-XX:TieredStopAtLevel=1",
			"-XX:CompileCommand=exclude,org/mockmc/mockmc/generated/*.*",
			"-XX:CompileCommand=exclude,org/mockmc/mockmc/*.*"
		)
	}

	compileTestJava {
		options.compilerArgs.addAll(listOf("-Xlint:deprecation", "-Xlint:removal", "-Xlint:unchecked", "-Xmaxwarns", "1000"))
		options.isFork = true
		options.forkOptions.memoryMaximumSize = "8g"
		options.forkOptions.jvmArgs = mutableListOf("-XX:TieredStopAtLevel=1", "-XX:+UseSerialGC")
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
			}
		}
	}

	test {
		dependsOn(project(":extra:TestPlugin").tasks.jar)
		useJUnitPlatform()
		ignoreFailures = true
	}

	check {
		dependsOn(jacocoTestReport)
	}

	jacocoTestReport {
		dependsOn(test)
		reports {
			xml.required.set(true)
			html.required.set(true)
			html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
		}
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
	// No key available to sign with for maven local
	if (!project.gradle.startParameter.taskNames.any { it.contains("publishToMavenLocal") }) {
		signAllPublications()
	}
}

signing {
	useGpgCmd()
}

fun isAction(): Boolean {
	return System.getenv("GITHUB_ACTIONS") == "true" && System.getenv("GITHUB_REPOSITORY") == "westkevin12/MockMC"
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

val backendJars = mapOf(
	"Folia" to "folia-1.21.11-14.jar",
	"Paper" to "paper-26.1.2-53.jar",
	"Spigot" to "spigot-26.1.2.jar",
	"Velocity" to "velocity-3.5.0-SNAPSHOT-593.jar",
	"Waterfall" to "waterfall-1.21-609.jar"
)

backendJars.forEach { (name, jarName) ->
	tasks.register<Test>("test$name") {
		group = "verification"
		description = "Runs tests against $name backend"
		dependsOn(project(":extra:TestPlugin").tasks.jar)
		useJUnitPlatform()

		val defaultClasspath = sourceSets.test.get().runtimeClasspath
		classpath = defaultClasspath + files("jars/$jarName")
		testClassesDirs = sourceSets.test.get().output.classesDirs

		doFirst {
			println("Running tests against backend: $name ($jarName)")
		}
	}
}

tasks.register("testAllBackends") {
	group = "verification"
	description = "Runs the test suite against all provided backend JARs"
	dependsOn("testFolia", "testPaper", "testSpigot", "testVelocity", "testWaterfall")
}

configurations.all {
	resolutionStrategy {
		force("com.mojang:brigadier:1.3.10")
	}
}

