package org.example

import java.nio.file.Files
import java.nio.file.Paths

interface InputLines {
    fun process(lines: List<String>): String

    companion object {
        fun fromFile(fileName: String): List<String> {
            return Files.readAllLines(Paths.get(fileName))
        }
    }
}