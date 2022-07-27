import java.awt.Desktop

val SRC_ROOT = "src/main/java"
val TESTS_PATTERN = "**/tests/**"


plugins {
    java
    `maven-publish`
    id("com.github.ben-manes.versions") version "+"
}


repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:+")
    implementation("org.springframework.boot:spring-boot-starter-web:+")
    implementation("com.google.guava:guava:+")
    testImplementation("com.tngtech.archunit:archunit-junit5:+")
    runtimeOnly("com.h2database:h2:+")
}

group = "com.tejasoft.sboot"
version = "0.0.1-SNAPSHOT"
description = "sboot-api-archunit"

publishing {
    publications.create<MavenPublication>("maven") {
	from(components["java"])
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<Copy> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    includeEmptyDirs = false
}

sourceSets {
    main {
	java {
	    srcDir(SRC_ROOT)
	    exclude(TESTS_PATTERN)
	}

	resources {
	    srcDir(SRC_ROOT + "/com/tejasoft/sboot/res")
	}
    }

    test {

	java {
	    srcDir(SRC_ROOT)
	    include(TESTS_PATTERN)
	}

	resources {
	    srcDir(SRC_ROOT + "/com/tejasoft/sboot/tests/archunit/res")
	}
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    ignoreFailures = true
}

tasks.register("openTR") {
    doLast {
	project.buildDir
	Desktop.getDesktop().browse(File("$buildDir/reports/tests/test/index.html").toURI())
    }
}

defaultTasks("clean", "test", "openTR")