package br.com.phs.utils

import java.io.FileInputStream
import java.util.*

fun getProjectProperties(): Properties {
    val properties = Properties()
    properties.load(FileInputStream("gradle.properties"))
    return properties
}
fun getApplicationVersion(): String = getProjectProperties().getProperty("application.version")
