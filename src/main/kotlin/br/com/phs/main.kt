package br.com.phs

import br.com.phs.utils.*
import java.io.File
import java.nio.file.Paths
import java.util.*
import kotlin.system.exitProcess

val envVars: MutableMap<String, String> = System.getenv()
val homeDir = envVars["ELEN_HOME"]
private var debugMode = false

fun main(args: Array<String>?) {

    if (args.isNullOrEmpty()) {
        println(errorContent)
        exitProcess(INVALID_ARGS_ERROR_EXIT)
    }

    if (!args.contains("-d") && args.size > 1) {
        println(argumentsErrorContent)
        exitProcess(INVALID_ARGS_ERROR_EXIT)
    }

    debugMode = args.contains("-d")

    if (debugMode) {
        println("Arguments: ${args.joinToString(separator = ",")}")
        println("HOME DIR: $homeDir")
        val currentPath = Paths.get("").toAbsolutePath().toString()
        println("O aplicativo está sendo executado em: $currentPath")
    }

    val (isAdd, index) = if (args.size == 2 && args[1].contains("add")) {
        Pair(true, 1)
    } else if (args[0].contains("add")) {
        Pair(true, 0)
    } else {
        Pair(false, -1)
    }

    when {
        "help" in args || "-h" in args -> { printHelpContent() }
        "version" in args || "-v" in args -> { printVersion() }
        "init" in args || "-i" in args -> { processUserEntrance() }
        isAdd -> {
            val dependence = args[index].substringAfter(":")
            if (debugMode) {
                println("Dependence: $dependence")
            }
            addDependence(dependence)
        }
        "build" in args || "-b" in args -> { executeCommand("build") }
        "clean" in args || "-c" in args -> { executeCommand("clean") }
        "run" in args || "-r" in args -> { executeCommand("run") }
        else -> {
            println("Argument error. Please, to help type help or -h")
            exitProcess(INVALID_ARGS_ERROR_EXIT)
        }
    }


}

private fun printVersion() {
    println(welcomeContent)
    exitProcess(EXIT_OK)
}

private fun printHelpContent() {
    println(helpContent)
    exitProcess(EXIT_OK)
}

private fun processUserEntrance() {

    println(welcomeContent)

    val scanner = Scanner(System.`in`)
    val projectData: List<String>

    do {

        projectData = setup(scanner)
        val (projectName, packageName, version) = projectData
        print("Create project $projectName package $packageName version $version? Yes/No [N]: ")
        val choose = scanner.nextLine()
        if (choose.equals("Y", true)) {
            break
        } else {
            println("Canceled by user.")
            exitProcess(CANCEL_EXIT)
        }

    } while(true)

    val (projectName, packageName, version) = projectData
    println("Creating project $projectName package $packageName version $version")
    createBuildGradleKT(projectData)
    createMainKT(projectData)

    val source = Paths.get("$homeDir/resources/gradleres")
    val currentDir = Paths.get("").toAbsolutePath()

    if (debugMode) {
        println("source: ${source.toAbsolutePath()}")
        println("currentDir: ${currentDir.toAbsolutePath()}")
    }

    FileUtils.copyDirectory(source, currentDir)

}

private fun setup(scanner: Scanner): List<String> {

    val userName = System.getProperty("user.name")
    val defaultProjectName = "unanmed"
    val defaultPackageName = "me.$userName"
    val defaultVersion = "1.0-SNAPSHOT"
    println("Initializing Gradle Kotlin Project.")

    print("Type project nema [$defaultProjectName]: ")
    var projectName = scanner.nextLine()
    if (projectName.isNullOrEmpty()) {
        projectName = defaultProjectName
    }

    print("Type package nema [$defaultPackageName]: ")
    var packageName = scanner.nextLine()
    if (packageName.isNullOrEmpty()) {
        packageName = defaultPackageName
    }

    print("Type package nema [$defaultVersion]: ")
    var version = scanner.nextLine()
    if (version.isNullOrEmpty()) {
        version = defaultVersion
    }

    return listOf(projectName, packageName, version)

}

private fun createBuildGradleKT(projectData: List<String>) {

    val (projectName, packageName, version) = projectData
    val buildGradleKT = """
        plugins {
            kotlin("jvm") version "1.8.20"
            application
        }

        group = "$packageName"
        version = "$version"

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
            mainClass.set("$packageName.MainKt")
        }
    """.trimIndent()

    val gradleProperties = "kotlin.code.style=official"

    val settingsGradleKT = """
        rootProject.name = "$projectName"
    """.trimIndent()

    val filesList = listOf(buildGradleKT, gradleProperties, settingsGradleKT)
    val filesNameList = listOf("build.gradle.kts", "build.properties", "settings.gradle.kts")
    filesList.forEachIndexed { index, file ->
        File(filesNameList[index]).writeText(file)
    }

}

private fun createMainKT(projectData: List<String>) {

    val (_, packageName, _) = projectData
    val mainKT = """
        package $packageName
        
        fun main(args: Array<String>) {
            println(args.contentToString())
        }
        
    """.trimIndent()

    val packageFolders = packageName.split(".")
    val folders = packageFolders.joinToString(separator = "/")
    val path = "./src/main/kotlin/$folders"
    File(path).mkdirs()
    File(path, "main.kt").writeText(mainKT)

}