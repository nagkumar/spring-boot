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
    implementation("com.tngtech.archunit:archunit-junit5:1.0.0-rc1")
    runtimeOnly("com.h2database:h2:2.1.214")
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
description = "api-example-archunit"

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
}

sourceSets {
    main {
	java {
	    srcDir("src/main/java")
	    exclude("**/test/**")
	}

	resources {
	    srcDir("src/main/resources")
	    exclude("**/test/**")
	}
    }

    test {
	java {
	    srcDir("src/main/java")
	    include("**/test/**")
	}

	resources {
	    srcDir("src/main/resources/test")
	}
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}