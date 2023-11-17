plugins {
    java
    jacoco
    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.1.3"
    id("org.graalvm.buildtools.native") version "0.9.27"
    id("org.sonarqube") version "4.4.1.3373"
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

sonar {
    properties {
        property("sonar.projectKey", "Joystick-Judgement_joystick-judgement-backend")
        property("sonar.organization", "joystick-judgement")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.coverage.jacoco.xmlReportPaths", "build/reports/jacoco/test/jacocoTestReport.xml")
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementations(
        "org.springframework.boot:spring-boot-starter-actuator",
        "org.springframework.boot:spring-boot-starter-hateoas",
        "org.springframework.boot:spring-boot-starter-validation",
        "org.springframework.boot:spring-boot-starter-data-jpa",
        "org.springframework.boot:spring-boot-starter-web",
        "org.liquibase:liquibase-core",
        "org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0",
    )
    compileOnly("org.projectlombok:lombok")
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

        jacocoTestReport {
            dependsOn(test)
            reports {
                xml.required = true
            }
        }
        finalizedBy(jacocoTestReport)

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
