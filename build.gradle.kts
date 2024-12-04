dependencies {
    // Spring Boot dependencies
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa") // JPA starter

    // Jakarta dependencies for JPA
    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")

    // Lombok for reducing boilerplate code
    implementation("org.projectlombok:lombok:1.18.24")

    // PostgreSQL driver for database connection
    runtimeOnly("org.postgresql:postgresql:42.5.1")  // PostgreSQL driver

    // Development-only dependencies
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    // Test dependencies
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

