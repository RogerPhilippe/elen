package br.com.phs.utils

import java.io.*
import java.nio.file.Paths
import java.util.*

fun getProjectProperties(): Properties {
    val properties = Properties()
    properties.load(FileInputStream("gradle.properties"))
    return properties
}
fun getApplicationVersion(): String = getProjectProperties().getProperty("application.version")

fun executeCommand(command: String) {

    val gradleW = if (System.getProperty("os.name").startsWith("Windows")) "gradlew.bat" else "gradlew"
    val currentDir = File("").absolutePath
    val gradleScript = "$currentDir${File.separator}$gradleW"

    try {
        val process = ProcessBuilder(gradleScript, command).inheritIO().start()
        process.waitFor()
    } catch (e: IOException) {
        e.printStackTrace()
    }

}