plugins {
    id("org.springframework.cloud.contract") version "4.1.3"
    id("maven-publish")
}

dependencies {

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("org.postgresql:postgresql")

    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-database-postgresql")

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")

    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.security:spring-security-oauth2-jose")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")

    testImplementation("org.springframework.cloud:spring-cloud-starter-contract-verifier")

    implementation(project(":common"))
}

contracts {
    contractsDslDir.set(file("$rootDir/services/exchange/src/test/resources/contracts"))
    baseClassForTests.set("ru.abramov.practicum.bank.service.exchange.contract.BaseContractTest")
}

publishing {
    publications {
        create<MavenPublication>("stubs") {
            groupId = "ru.abramov.practicum.bank"
            artifactId = "${project.name}-stubs"
            version = "0.0.1-SNAPSHOT"

            artifact(tasks.named("verifierStubsJar"))
        }
    }

    repositories {
        mavenLocal()
    }
}

openApiGenerate {
    generatorName.set("spring")
    inputSpec.set("$rootDir/services/exchange/src/main/resources/openapi.yaml")
    outputDir.set("$rootDir/clients/exchange")
    apiPackage.set("ru.abramov.practicum.bank.client.exchange.api")
    modelPackage.set("ru.abramov.practicum.bank.client.exchange.model")
    invokerPackage.set("ru.abramov.practicum.bank.client.exchange.invoker")
    configOptions.set(
        mapOf(
            "library" to "spring-cloud",
            "useResponseEntity" to "false",
            "useTags" to "true",
            "dateLibrary" to "java8",
            "useJakartaEe" to "true"
        )
    )
}

tasks.jar {
    enabled = false
}

tasks.bootJar {
    archiveFileName = "app.jar"
    mainClass.set("ru.abramov.practicum.bank.service.exchange.ExchangeServicePracticumBankApplication")
}

tasks.bootRun {
    mainClass.set("ru.abramov.practicum.bank.service.exchange.ExchangeServicePracticumBankApplication")
}
