
dependencies {
}

tasks.jar {
    enabled = false
}

tasks.bootJar {
    archiveFileName = "app.jar"
    mainClass.set("ru.abramov.practicum.bank.service.account.AccountServicePracticumBankApplication")
}

tasks.bootRun {
    mainClass.set("ru.abramov.practicum.bank.service.account.AccountServicePracticumBankApplication")
}
