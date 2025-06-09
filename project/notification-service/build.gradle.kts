
dependencies {
}

tasks.jar {
    enabled = false
}

tasks.bootJar {
    archiveFileName = "app.jar"
    mainClass.set("ru.abramov.practicum.bank.service.notification.NotificationServicePracticumBankApplication")
}

tasks.bootRun {
    mainClass.set("ru.abramov.practicum.bank.service.notification.NotificationServicePracticumBankApplication")
}
