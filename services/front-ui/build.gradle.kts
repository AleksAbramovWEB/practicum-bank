
dependencies {
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

    implementation("org.keycloak:keycloak-admin-client:26.0.5")

    testImplementation("org.springframework.security:spring-security-test")

    testImplementation("org.springframework.cloud:spring-cloud-starter-contract-stub-runner")

    implementation(project(":common"))
    implementation(project(":clients:account"))
    implementation(project(":clients:cash"))
    implementation(project(":clients:transfer"))
}

tasks.jar {
    enabled = false
}

tasks.bootJar {
    archiveFileName = "app.jar"
    mainClass.set("ru.abramov.practicum.bank.ui.FrontUiPracticumBankApplication")
}

tasks.bootRun {
    mainClass.set("ru.abramov.practicum.bank.ui.FrontUiPracticumBankApplication")
}
