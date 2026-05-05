plugins {
	id("java")
	id("xyz.jpenilla.run-paper") version "3.0.2"
	id("com.gradleup.shadow") version "9.4.1"
	id("jacoco")
}

jacoco {
	toolVersion = "0.8.14"
}

group = "io.github.secondlifegaming"
version = "1.2-SNAPSHOT"

repositories {
	mavenCentral()
	maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
	implementation("io.papermc.paper:paper-api:${rootProject.property("paper.api.full-version")}")
	implementation("commons-io:commons-io:2.18.0")
	implementation("com.squareup:javapoet:1.13.0")
	implementation("io.leangen.geantyref:geantyref:1.3.16")
	implementation("com.google.code.gson:gson:2.11.0")
	implementation("net.md-5:SpecialSource:1.11.0")
    
    // Force modern ASM 9.8 for Java 25 support
    val asmVersion = "9.8"
    implementation("org.ow2.asm:asm:$asmVersion")
    implementation("org.ow2.asm:asm-commons:$asmVersion")
    implementation("org.ow2.asm:asm-tree:$asmVersion")
    implementation("org.ow2.asm:asm-analysis:$asmVersion")
    
	implementation("org.tukaani:xz:1.10")
	implementation("io.sigpipe:jbsdiff:1.0")
	implementation("org.apache.commons:commons-compress:1.27.1")

	testImplementation("org.junit.jupiter:junit-jupiter:6.0.3")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

configurations.all {
    resolutionStrategy.force("org.ow2.asm:asm:9.8", "org.ow2.asm:asm-commons:9.8", "org.ow2.asm:asm-tree:9.8", "org.ow2.asm:asm-analysis:9.8")
}

tasks {
	compileJava {
		options.encoding = Charsets.UTF_8.name()
		options.release.set(25)
	}

	processResources {
		val props = mapOf("version" to project.version)
		inputs.properties(props)
		filesMatching("**/plugin.yml") {
			expand(props)
		}
	}

	test {
		dependsOn(project(":extra:TestPlugin").tasks.jar)
		useJUnitPlatform()
		maxParallelForks = 1
		jvmArgs("-Xint")
		systemProperty("junit.jupiter.execution.parallel.enabled", "false")
		ignoreFailures = true
	}

	shadowJar {
		relocate("com.squareup.javapoet", "org.mockmc.metaminer.javapoet")
	}

	build {
		dependsOn(shadowJar)
	}

	runServer {
		pluginJars.setFrom(shadowJar.flatMap { it.archiveFile })
	}

	register<JavaExec>("runGenerator") {
		group = "application"
		mainClass.set("org.mockmc.metaminer.StandaloneRunner")
		classpath = sourceSets.main.get().runtimeClasspath
		args(rootProject.projectDir.absolutePath)
		standardOutput = System.out
		errorOutput = System.err
		
		val javaToolchains = project.extensions.getByType<JavaToolchainService>()
		javaLauncher.set(javaToolchains.launcherFor {
			languageVersion.set(JavaLanguageVersion.of(25))
		})

		doFirst {
			println("Running Generator with Classpath: ${classpath.asPath}")
		}
	}

	jacocoTestReport {
		dependsOn(test)
		reports {
			xml.required.set(true)
			html.required.set(true)
		}
	}

	check {
		dependsOn(jacocoTestReport)
	}
}
