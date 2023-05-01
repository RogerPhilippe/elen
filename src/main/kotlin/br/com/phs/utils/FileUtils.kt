package br.com.phs.utils

import java.nio.file.Files
import java.nio.file.Path
object FileUtils {

    @JvmStatic
    fun copyDirectory(source: Path, target: Path) {
        Files.walk(source).forEach { sourcePath ->
            val targetPath = target.resolve(source.relativize(sourcePath))
            if (Files.isDirectory(sourcePath)) {
                Files.createDirectories(targetPath)
            } else {
                Files.copy(sourcePath, targetPath)
            }
        }
    }


}