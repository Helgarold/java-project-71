plugins {
    id("java")
    id("checkstyle")
    id("application")
    id("com.github.johnrengelman.shadow") version "8.0.0" // Обновите до последней версии
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
    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.2")  // Обновите до последней стабильной версии
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

tasks.jacocoTestReport {
    additionalSourceDirs.setFrom(files("src/main/java"))
    reports {
        xml.required.set(true)
        html.required.set(true)
    }

    additionalSourceDirs.setFrom(files("src/main/java")) // Убедитесь, что файл и пути настроены правильно
}

tasks.jacocoTestCoverageVerification { // Отчет о покрытии тестов
    dependsOn(tasks.jacocoTestReport) // Убедитесь, что отчет создается перед проверкой
}
