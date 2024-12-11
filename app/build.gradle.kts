plugins {
    id("java")
    id("checkstyle")
    id("application")
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("jacoco")
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
    // Обновите версии библиотек до актуальных
    implementation("info.picocli:picocli:4.6.1")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.2")
    testImplementation(platform("org.junit:junit-bom:5.10.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

application {
    mainClass.set("hexlet.code.App")
}

tasks.test {
    useJUnitPlatform()
}

jacoco {
    toolVersion = "0.8.12"
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    additionalSourceDirs.setFrom(files("src/main/java"))
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}

tasks.jacocoTestCoverageVerification {
    dependsOn(tasks.jacocoTestReport)
}
