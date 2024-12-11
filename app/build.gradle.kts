plugins {
    id("java")
    id("checkstyle")
    id("application")
    id("com.github.johnrengelman.shadow") version "7.1.0"
    id("jacoco") // Плагин для Jacoco
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

checkstyle {
    toolVersion = "8.45"
    configFile = file("config/checkstyle/checkstyle.xml")
}

tasks.named<Checkstyle>("checkstyleMain") {
    dependsOn(tasks.compileJava)
    source = project.fileTree("src/main/java")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("info.picocli:picocli:4.6.1")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.0")  // Убедитесь, что версия актуальна
}

application {
    mainClass.set("hexlet.code.App") // Убедитесь, что это основной класс в вашем проекте
}

// Задаем параметры для тестов
tasks.test {
    useJUnitPlatform()
}

jacoco {
    toolVersion = "0.8.8" // Убедитесь, что используете нужную версию Jacoco
}

tasks.jacocoTestReport { // Задача для генерации отчета Jacoco
    dependsOn(tasks.test) // Запускаем тесты перед генерацией отчета
    finalizedBy(tasks.jacocoTestCoverageVerification) // Используется для проверки покрытия
    reports {
        xml.required.set(true) // Убедитесь, что XML отчет создается
        html.required.set(true) // Убедитесь, что HTML отчет создается
    }
}

tasks.jacocoTestCoverageVerification { // Отчет о покрытии тестов
    dependsOn(tasks.jacocoTestReport) // Убедитесь, что отчет создается перед проверкой
}
