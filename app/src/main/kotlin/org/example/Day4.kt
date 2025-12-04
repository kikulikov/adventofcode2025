package org.example

import org.example.InputLines.Companion.fromFileAsList

/**
 * [--- Day 4: Printing Department ---](https://adventofcode.com/2025/day/4)
 */
private val dataSample = """
    ..@@.@@@@.
    @@@.@.@.@@
    @@@@@.@.@@
    @.@@@@..@.
    @@.@@@@.@@
    .@@@@@@@.@
    .@.@.@.@@@
    @.@@@.@@@@
    .@@@@@@@@.
    @.@.@@@.@.
""".trimIndent()

private val inputFile = """
    app/src/main/resources/day4.txt
""".trimIndent()

fun main() {
    println()
    println("[Part 1] Answer: " + Day4Part1().process(dataSample.lines()))
    println("[Part 1] Answer: " + Day4Part1().process(fromFileAsList(inputFile)))
    println()
    println("[Part 2] Answer: " + Day4Part2().process(dataSample.lines()))
    println("[Part 2] Answer: " + Day4Part2().process(fromFileAsList(inputFile)))
}

private class Day4Part2 : InputLines {

    override fun process(lines: List<String>): String {

        val arr = convertToArray(lines)
        var updated = true
        var sum = 0L

        while (updated) {
            updated = false

            for (i in 0 until arr.size) {
                for (j in 0 until arr[i].size) {
                    if (arr[i][j] == 1) {
                        val count = countAdjacent(j, arr, i)

                        if (count < 4) {
                            updated = true // mark that we updated
                            sum += 1 // count the removed roll
                            arr[i][j] = 0 // remove the roll
                        }
                    }
                }
            }
        }

        return sum.toString()
    }
}

private class Day4Part1 : InputLines {

    override fun process(lines: List<String>): String {
        var sum = 0L

        val arr = convertToArray(lines)

        for (i in 0 until arr.size) {
            for (j in 0 until arr[i].size) {
                if (arr[i][j] == 1) {
                    val count = countAdjacent(j, arr, i)
                    sum += if (count < 4) 1 else 0 // count the removed roll
                }
            }
        }

        return sum.toString()
    }
}

private fun countAdjacent(j: Int, arr: Array<IntArray>, i: Int): Int {
    var count = 0

    // left
    if (j - 1 >= 0 && arr[i][j - 1] == 1) {
        count += 1
    }

    // right
    if (j + 1 < arr[i].size && arr[i][j + 1] == 1) {
        count += 1
    }

    // up
    if (i - 1 >= 0 && arr[i - 1][j] == 1) {
        count += 1
    }

    // down
    if (i + 1 < arr.size && arr[i + 1][j] == 1) {
        count += 1
    }

    // left-up
    if (i - 1 >= 0 && j - 1 >= 0 && arr[i - 1][j - 1] == 1) {
        count += 1
    }

    // right-up
    if (i - 1 >= 0 && j + 1 < arr[i].size && arr[i - 1][j + 1] == 1) {
        count += 1
    }

    // left-down
    if (i + 1 < arr.size && j - 1 >= 0 && arr[i + 1][j - 1] == 1) {
        count += 1
    }

    // right-down
    if (i + 1 < arr.size && j + 1 < arr[i].size && arr[i + 1][j + 1] == 1) {
        count += 1
    }

    return count
}

private fun convertToArray(lines: List<String>): Array<IntArray> {
    val arr = Array(lines.size) { IntArray(lines[0].length) }

    for (i in 0 until lines.size) {
        for (j in 0 until lines[i].length) {
            arr[i][j] = if (lines[i][j] == '@') 1 else 0
        }
    }
    return arr
}