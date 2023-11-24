import Build_gradle.DependencyVersions.MAPSTRUCT_VERSION
import Build_gradle.DependencyVersions.SPRING_VERSION

plugins {
    java
    jacoco
    id("org.springframework.boot") version "3.2.0"
    id("io.spring.dependency-management") version "1.1.3"
    id("org.graalvm.buildtools.native") version "0.9.27"
    id("org.sonarqube") version "4.4.1.3373"
}

group = "com.joystick-judgement"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

object DependencyVersions {
    const val SPRING_VERSION = "3.2.0"
    const val MAPSTRUCT_VERSION = "1.5.5.Final"
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
        "org.springframework.boot:spring-boot-starter-actuator:$SPRING_VERSION",
        "org.springframework.boot:spring-boot-starter-hateoas:$SPRING_VERSION",
        "org.springframework.boot:spring-boot-starter-validation:$SPRING_VERSION",
        "org.springframework.boot:spring-boot-starter-data-jpa:$SPRING_VERSION",
        "org.springframework.boot:spring-boot-starter-web:$SPRING_VERSION",
        "org.mapstruct:mapstruct:$MAPSTRUCT_VERSION",
        "org.liquibase:liquibase-core:4.24.0",
        "org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0",
    )
    compileOnly("org.projectlombok:lombok:1.18.26")
    developmentOnly("org.springframework.boot:spring-boot-docker-compose:$SPRING_VERSION")
    runtimeOnly("org.postgresql:postgresql:42.5.4")
    annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")
    annotationProcessor("org.mapstruct:mapstruct-processor:$MAPSTRUCT_VERSION")
    testAnnotationProcessor("org.mapstruct:mapstruct-processor:$MAPSTRUCT_VERSION")
    testImplementation("org.springframework.boot:spring-boot-starter-test:$SPRING_VERSION")
}


tasks {


    withType<JavaCompile>().configureEach {
        options.isFork = true
        options.compilerArgs.plusAssign(
            listOf(
                "-Amapstruct.defaultComponentModel=spring",
                "-Amapstruct.defaultInjectionStrategy=constructor"
            )
        )
    }

    withType<Test> {

        jacocoTestReport {
            dependsOn(test)
            reports {
                xml.required = true
            }
            classDirectories.setFrom(
                files(classDirectories.files.map {
                    fileTree(it) {
                        exclude(
                            "com/joystickjudgement/msmovie/configurations/**",
                            "com/joystickjudgement/msmovie/enums/**",
                        )
                    }
                })
            )
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