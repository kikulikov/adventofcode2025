package org.example

import org.example.InputLines.Companion.fromFileAsList

/**
 * [--- Day 6: Trash Compactor ---](https://adventofcode.com/2025/day/6)
 */
private val dataSample = """
    123 328  51 64 
     45 64  387 23 
      6 98  215 314
    *   +   *   +  
""".trimIndent()

private val inputFile = """
    app/src/main/resources/day6.txt
""".trimIndent()

fun main() {
    println()
    println("[Part 1] Answer: " + Day6Part1().process(dataSample.lines()))
    println("[Part 1] Answer: " + Day6Part1().process(fromFileAsList(inputFile)))
    println()
    println("[Part 2] Answer: " + Day6Part2().process(dataSample.lines()))
    println("[Part 2] Answer: " + Day6Part2().process(fromFileAsList(inputFile)))
}

private class Day6Part2 : InputLines {

    override fun process(lines: List<String>): String {
        var sum = 0L

        val n = lines[0].length
        val m = lines.size

        var columnValues = ArrayList<String>()
        var columnOperator = ""

        for (j in 0 until n) {
            var value = ""

            for (i in 0 until m - 1) {
                value += lines[i].substring(j, j + 1)
            }

            columnValues.add(value.trim())
            columnOperator += lines[m - 1].substring(j, j + 1).trim()

            if (j == n - 1 || value.isBlank()) {
                when (columnOperator) {
                    "+" -> sum += columnValues.filter { it.isNotBlank() }.sumOf { it.toLong() }
                    "*" -> sum += columnValues.filter { it.isNotBlank() }
                        .map { it.toLong() }.fold(1L) { acc, v -> acc * v }
                }

                columnValues = ArrayList()
                columnOperator = ""
                continue
            }
        }

        return sum.toString()
    }
}

private class Day6Part1 : InputLines {

    override fun process(lines: List<String>): String {
        var sum = 0L
        val n = lines[0].trim().split(Regex("\\s+")).size
        val m = lines.size - 1 // last line is operators
        val arr = Array(m) { LongArray(n) }

        for (i in 0 until m) {
            val tokens = lines[i].trim().split(Regex("\\s+"))

            for (j in 0 until n) {
                arr[i][j] = tokens[j].trim().toLong()
            }
        }

        // println(arr.contentDeepToString())

        val tokens = lines[m].trim().split(Regex("\\s+"))

        for (j in 0 until n) {
            val operator = tokens[j]
            var columnResult = 0L

            for (i in 0 until m) {
                when (operator) {
                    "+" -> columnResult += arr[i][j]
                    "*" -> columnResult = if (columnResult == 0L) arr[i][j] else columnResult * arr[i][j]
                }
            }

            sum += columnResult
        }

        return sum.toString()
    }
}
