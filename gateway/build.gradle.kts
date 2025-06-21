

dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-gateway-server-webflux")
}

tasks.jar {
    enabled = false
}

tasks.bootJar {
    archiveFileName = "app.jar"
    mainClass.set("ru.abramov.practicum.bank.gateway.GatewayApplication")
}

tasks.bootRun {
    mainClass.set("ru.abramov.practicum.bank.gateway.GatewayApplication")
}