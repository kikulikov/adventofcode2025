package org.example

import org.example.InputLines.Companion.fromFileAsList

/**
 * https://adventofcode.com/2025/day/1
 */
private class Day1Part2 : InputLines {

    override fun process(lines: List<String>): String {
        var count = 0
        var dial = 50

        for (line in lines) {
            count += line.substring(1).toInt() / 100
            val move = line.substring(1).toInt() % 100

            if (line.startsWith("L")) {
                if (dial > 0 && dial - move < 0) {
                    count += 1
                }
                dial = (dial - move + 100) % 100
            } else if (line.startsWith("R")) {
                if (dial + move > 100) {
                    count += 1
                }
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
    println("Answer: " + Day1Part2().process(dataSample.lines()))
    println("Answer: " + Day1Part2().process(fromFileAsList(inputFile)))
}
