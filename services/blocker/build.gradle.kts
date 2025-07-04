plugins {
    id("org.springframework.cloud.contract") version "4.1.3"
    id("maven-publish")
}

dependencies {
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")

    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.security:spring-security-oauth2-jose")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")

    implementation(project(":common"))

    testImplementation("org.springframework.cloud:spring-cloud-starter-contract-verifier")
}

contracts {
    contractsDslDir.set(file("$rootDir/services/blocker/src/test/resources/contracts"))
    baseClassForTests.set("ru.abramov.practicum.bank.service.blocker.contract.BaseContractTest")
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
    inputSpec.set("$rootDir/services/blocker/src/main/resources/openapi.yaml")
    outputDir.set("$rootDir/clients/blocker")
    apiPackage.set("ru.abramov.practicum.bank.client.blocker.api")
    modelPackage.set("ru.abramov.practicum.bank.client.blocker.model")
    invokerPackage.set("ru.abramov.practicum.bank.client.blocker.invoker")
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
    mainClass.set("ru.abramov.practicum.bank.service.blocker.BlockerServicePracticumBankApplication")
}

tasks.bootRun {
    mainClass.set("ru.abramov.practicum.bank.service.blocker.BlockerServicePracticumBankApplication")
}
