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

    implementation("org.springframework:spring-web:5.3.22")
    testImplementation("org.mockito:mockito-core:4.1.0")
    testImplementation("org.springframework.boot:spring-boot-test:2.7.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
    testImplementation("org.springframework.boot:spring-boot-test:2.7.2")
}

group = "com.example"
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
	    srcDir(SRC_ROOT)
	    include("**/sboot/res/**")
	    exclude(TESTS_PATTERN)
	}
    }

    test {
	java {
	    srcDir(SRC_ROOT)
	    include(TESTS_PATTERN)
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