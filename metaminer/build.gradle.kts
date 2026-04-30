plugins {
	id("java")
	id("io.papermc.paperweight.userdev") version "2.0.0-beta.21"
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
	paperweight.paperDevBundle("${rootProject.property("paper.api.full-version")}")
	implementation("io.papermc.paper:paper-api:${rootProject.property("paper.api.full-version")}")
	// implementation(project(":"))
	implementation("com.squareup:javapoet:1.13.0")
	implementation("io.leangen.geantyref:geantyref:1.3.16")
	implementation("com.google.code.gson:gson:2.11.0")


	// Dependencies for Unit Tests
	testImplementation("org.junit.jupiter:junit-jupiter:6.0.3")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
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
