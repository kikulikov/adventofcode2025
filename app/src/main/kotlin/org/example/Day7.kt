package org.example

import org.example.InputLines.Companion.fromFileAsList

/**
 * [--- Day 7: Laboratories ---](https://adventofcode.com/2025/day/7)
 */
private val dataSample = """
    .......S.......
    ...............
    .......^.......
    ...............
    ......^.^......
    ...............
    .....^.^.^.....
    ...............
    ....^.^...^....
    ...............
    ...^.^...^.^...
    ...............
    ..^...^.....^..
    ...............
    .^.^.^.^.^...^.
    ...............
""".trimIndent()

private val inputFile = """
    app/src/main/resources/day7.txt
""".trimIndent()

fun main() {
    println()
    println("[Part 1] Answer: " + Day7Part1().process(dataSample.lines()))
    println("[Part 1] Answer: " + Day7Part1().process(fromFileAsList(inputFile)))
    println()
    println("[Part 2] Answer: " + Day7Part2().process(dataSample.lines()))
    println("[Part 2] Answer: " + Day7Part2().process(fromFileAsList(inputFile)))
}

private class Day7Part2 : InputLines {

    override fun process(lines: List<String>): String {
        val splitters = LongArray(lines[0].length) { 0L }
        splitters[lines[0].indexOf("S")] = 1

        for (line in lines) {
            for (i in line.indices) {
                if (line[i] == '^') {
                    if (splitters[i] > 0) {
                        splitters[i - 1] += splitters[i]
                        splitters[i + 1] += splitters[i]
                        splitters[i] = 0L
                    }
                }
            }
        }

        return splitters.sum().toString()
    }
}

private class Day7Part1 : InputLines {

    override fun process(lines: List<String>): String {
        var sum = 0L

        val splitters = LongArray(lines[0].length) { 0L }
        splitters[lines[0].indexOf("S")] = 1

        for (line in lines) {
            for (i in line.indices) {
                if (line[i] == '^') {
                    if (splitters[i] > 0) {
                        sum += 1
                        splitters[i - 1] += splitters[i]
                        splitters[i + 1] += splitters[i]
                        splitters[i] = 0L
                    }
                }
            }
        }

        return sum.toString()
    }
}
