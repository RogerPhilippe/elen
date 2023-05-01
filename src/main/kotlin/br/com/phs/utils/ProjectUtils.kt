/*
 * Copyright [2023] [Roger Philippe Pereira] - rogerphilippe@outlook.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package br.com.phs.utils

import java.io.File
import java.io.FileInputStream
import java.io.IOException
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

fun addDependence(dependence: String) {

    val currentDir = File("").absolutePath
    val buildFile = File("$currentDir/build.gradle.kts")
    val lines = buildFile.readLines().toMutableList()

    val dependenciesIndex = lines.indexOfFirst { it.contains("dependencies") }
    if (dependenciesIndex != -1) {
        val newDependency = """
        |    implementation("$dependence")
        """.trimMargin()
        lines.add(dependenciesIndex + 1, newDependency)
        buildFile.writeText(lines.joinToString("\n"))
        println("Dependency added successfully")
        executeCommand("--refresh-dependencies")
    } else {
        println("Could not find the dependencies section in the file.")
    }

}