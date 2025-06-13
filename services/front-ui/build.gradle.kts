
dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-config")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    implementation("org.keycloak:keycloak-admin-client:26.0.5")


    implementation(project(":common"))
    implementation(project(":clients:account"))
    implementation(project(":clients:cash"))
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
