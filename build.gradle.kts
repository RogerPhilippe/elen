plugins {
    kotlin("jvm") version "1.8.20"
    application
}

group = "br.com.phs"
version = "${property("application.version")}"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}

application {
    mainClass.set("br.com.phs.MainKt")
}