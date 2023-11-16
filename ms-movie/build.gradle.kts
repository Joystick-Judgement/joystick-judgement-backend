plugins {
    java
    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.1.3"
    id("org.graalvm.buildtools.native") version "0.9.27"
}

group = "com.joystick-judgement"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementations(
        "org.springframework.boot:spring-boot-starter-actuator",
        "org.springframework.boot:spring-boot-starter-hateoas",
        "org.springframework.boot:spring-boot-starter-web",
        "org.liquibase:liquibase-core",
        "org.springdoc:springdoc-openapi-ui:1.7.0",
        "org.springdoc:springdoc-openapi-data-rest:1.7.0",
    )
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    developmentOnly("org.springframework.boot:spring-boot-docker-compose")
    runtimeOnly("org.postgresql:postgresql")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks {

    withType<JavaCompile>().configureEach {
        options.isFork = true
    }

    withType<Test> {
        useJUnitPlatform()
        systemProperties(
            "junit.jupiter.testinstance.lifecycle.default" to "per_class"
        )
        if (!project.hasProperty("createReports")) {
            reports.html.required = false
            reports.junitXml.required = false
        }
    }
    bootBuildImage {
        builder.set("paketobuildpacks/builder-jammy-tiny:latest")
    }
}

fun DependencyHandlerScope.implementations(vararg all: Any) {
    for (impl in all) implementation(impl)
}
