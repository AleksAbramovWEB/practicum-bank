plugins {
    java
    id("org.springframework.boot") version "3.5.0"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "ru.abramov.practicum"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

extra["springCloudVersion"] = "2025.0.0"

subprojects {
    apply(plugin = "java")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.springframework.boot")

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(21)
        }
    }

    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }

    dependencyManagement {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
        }
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
        compileOnly("org.projectlombok:lombok")
        developmentOnly("org.springframework.boot:spring-boot-devtools")
        developmentOnly("org.springframework.boot:spring-boot-docker-compose")
        runtimeOnly("org.postgresql:postgresql")
        annotationProcessor("org.projectlombok:lombok")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }


    tasks.withType<Test> {
        useJUnitPlatform()
    }
}