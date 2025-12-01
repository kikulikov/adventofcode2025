package org.example

import org.example.InputLines.Companion.fromFile

/**
 * https://adventofcode.com/2025/day/1
 */
class Day1Part1 : InputLines {

    override fun process(lines: List<String>): String {
        var count = 0
        var dial = 50

        for (line in lines) {
            val move = line.substring(1).toInt() % 100

            if (line.startsWith("L")) {
                dial = (dial - move + 100) % 100
            } else if (line.startsWith("R")) {
                dial = (dial + move) % 100
            }

            if (dial == 0) {
                count += 1
            }
        }
        return count.toString()
    }
}

private val dataSample = """
        L68
        L30
        R48
        L5
        R60
        L55
        L1
        L99
        R14
        L82
    """.trimIndent()

private const val inputFile = "app/src/main/resources/day1.txt"

fun main() {
    println(">>> " + Day1Part1().process(dataSample.lines()))
    println(">>> " + Day1Part1().process(fromFile(inputFile)))
}
