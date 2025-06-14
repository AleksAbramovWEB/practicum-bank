
dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-config")

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")

    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.security:spring-security-oauth2-jose")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")

    implementation("org.springframework.retry:spring-retry")
    implementation("org.springframework.boot:spring-boot-starter-aop")

    implementation(project(":common"))
    implementation(project(":clients:account"))
    implementation(project(":clients:blocker"))
    implementation(project(":clients:notification"))
}

openApiGenerate {
    generatorName.set("spring")
    inputSpec.set("$rootDir/services/cash/src/main/resources/openapi.yaml")
    outputDir.set("$rootDir/clients/cash")
    apiPackage.set("ru.abramov.practicum.bank.client.cash.api")
    modelPackage.set("ru.abramov.practicum.bank.client.cash.model")
    invokerPackage.set("ru.abramov.practicum.bank.client.cash.invoker")
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
    mainClass.set("ru.abramov.practicum.bank.service.cash.CashServicePracticumBankApplication")
}

tasks.bootRun {
    mainClass.set("ru.abramov.practicum.bank.service.cash.CashServicePracticumBankApplication")
}
