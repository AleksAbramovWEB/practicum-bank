
dependencies {
    implementation("org.springframework.kafka:spring-kafka")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")

    testImplementation("org.springframework.cloud:spring-cloud-starter-contract-stub-runner")

    implementation(project(":common"))
    implementation(project(":clients:exchange"))
}

tasks.jar {
    enabled = false
}

tasks.bootJar {
    archiveFileName = "app.jar"
    mainClass.set("ru.abramov.practicum.bank.service.generator.exchange.ExchangeGeneratorServicePracticumBankApplication")
}

tasks.bootRun {
    mainClass.set("ru.abramov.practicum.bank.service.generator.exchange.ExchangeGeneratorServicePracticumBankApplication")
}
