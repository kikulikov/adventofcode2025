package org.example

import org.example.InputLines.Companion.fromFileAsList

/**
 * [--- Day 3: Lobby ---](https://adventofcode.com/2025/day/3)
 */
private val dataSample = """
    987654321111111
    811111111111119
    234234234234278
    818181911112111
""".trimIndent()

private val inputFile = """
    app/src/main/resources/day3.txt
""".trimIndent()

fun main() {
    println()
    println("[Part 1] Answer: " + Day3Part1().process(dataSample.lines()))
    println("[Part 1] Answer: " + Day3Part1().process(fromFileAsList(inputFile)))
    println()
    println("[Part 2] Answer: " + Day3Part2().process(dataSample.lines()))
    println("[Part 2] Answer: " + Day3Part2().process(fromFileAsList(inputFile)))
}

private class Day3Part1 : InputLines {

    override fun process(lines: List<String>): String {
        var sum = 0L

        for (line in lines) {
            val (upperDigit, upperIndex) = findMax(line, 0, line.length - 1)
            val (lowerDigit, _) = findMax(line, upperIndex + 1, line.length)
            sum += upperDigit * 10 + lowerDigit
        }

        return sum.toString()
    }

    private fun findMax(line: String, left: Int, right: Int): Pair<Int, Int> {
        var maxDigit = line[left].digitToInt()
        var maxIndex = left

        for (i in left + 1 until right) {
            if (line[i].digitToInt() > maxDigit) {
                maxDigit = line[i].digitToInt()
                maxIndex = i
            }
        }
        return Pair(maxDigit, maxIndex)
    }
}

private class Day3Part2 : InputLines {

    override fun process(lines: List<String>): String {
        var sum = 0L

        for (line in lines) {

            var largestJoltage = 0L
            var indexLeft = 0
            var indexRight = line.length - 11

            repeat(12) {
                val (upperDigit, upperIndex) = findMax(line, indexLeft, indexRight)
                largestJoltage = largestJoltage * 10 + upperDigit
                indexLeft = upperIndex + 1
                indexRight += 1
            }

            sum += largestJoltage
        }

        return sum.toString()
    }

    private fun findMax(line: String, left: Int, right: Int): Pair<Int, Int> {
        var maxDigit = line[left].digitToInt()
        var maxIndex = left

        for (i in left + 1 until right) {
            if (line[i].digitToInt() > maxDigit) {
                maxDigit = line[i].digitToInt()
                maxIndex = i
            }
        }
        return Pair(maxDigit, maxIndex)
    }
}