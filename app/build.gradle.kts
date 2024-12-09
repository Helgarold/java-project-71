plugins {
    id("java")
    id("application")
    id("com.github.johnrengelman.shadow") version "7.1.0"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("info.picocli:picocli:4.6.1")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.0")  // Добавьте эту строку
}

application {
    mainClass.set("hexlet.code.App") // Убедитесь, что это основной класс в вашем проекте
}

tasks.test {
    useJUnitPlatform()
}
