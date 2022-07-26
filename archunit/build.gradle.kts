val SRC_ROOT = "src/main/java"
val TEST_PATTERN = "**/tests/**"

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

group = "com.example"
version = "0.0.1-SNAPSHOT"
description = "sboot-api-archunit"

publishing {
    publications.create<MavenPublication>("maven") {
	from(components["java"])
    }
}

tasks.withType<JavaCompile>() {
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
	    exclude(TEST_PATTERN)
	}

	resources {
	    srcDir(SRC_ROOT)
	    include("**/sboot/res/**")
	    exclude(TEST_PATTERN)
	}
    }

    test {
	java {
	    srcDir(SRC_ROOT)
	    include(TEST_PATTERN)
	}

	resources {
	    srcDir(SRC_ROOT)
	    include("**/tests/archunit/res/**")
	}
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}