import java.awt.Desktop

val SRC_ROOT = "src/main/java"
val TESTS_PATTERN = "**/tests/**"


plugins {
    java
    `maven-publish`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.7.2")
    implementation("org.springframework.boot:spring-boot-starter-web:2.7.2")
    implementation("com.google.guava:guava:31.1-jre")
    testImplementation("com.tngtech.archunit:archunit-junit5:1.0.0-rc1")
    runtimeOnly("com.h2database:h2:2.1.214")
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