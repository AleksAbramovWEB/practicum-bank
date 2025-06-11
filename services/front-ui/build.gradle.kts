
dependencies {
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    implementation("org.keycloak:keycloak-admin-client:26.0.5")

    implementation(project(":common"))
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
