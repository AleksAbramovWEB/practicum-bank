
dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-config")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")

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
