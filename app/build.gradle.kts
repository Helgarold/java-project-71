plugins {
    id("java")
    id("checkstyle")
    id("application")
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
    setSource(project.fileTree("src/main/java"))
}

dependencies {
    implementation("info.picocli:picocli:4.6.1")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.2")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.14.2") // Для работы с YAML

    // Добавление библиотеки SnakeYAML, если требуется
    implementation("org.yaml:snakeyaml:1.29")

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

    reports {
        xml.required.set(true)
        html.required.set(true)
        xml.outputLocation.set(file("$buildDir/reports/jacoco/test/jacocoTestReport.xml"))
        html.outputLocation.set(file("$buildDir/reports/jacoco/test/html"))
    }
}

tasks.jacocoTestCoverageVerification {
    dependsOn(tasks.jacocoTestReport)
}
