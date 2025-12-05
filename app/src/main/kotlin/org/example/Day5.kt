package org.example

import org.example.InputLines.Companion.fromFileAsList

/**
 * [--- Day 5: Cafeteria ---](https://adventofcode.com/2025/day/5)
 */
private val dataSample = """
    3-5
    10-14
    16-20
    12-18
    
    1
    5
    8
    11
    17
    32
""".trimIndent()

private val inputFile = """
    app/src/main/resources/day5.txt
""".trimIndent()

fun main() {
    println()
    println("[Part 1] Answer: " + Day5Part1().process(dataSample.lines()))
    println("[Part 1] Answer: " + Day5Part1().process(fromFileAsList(inputFile)))
    println()
    println("[Part 2] Answer: " + Day5Part2().process(dataSample.lines()))
    println("[Part 2] Answer: " + Day5Part2().process(fromFileAsList(inputFile)))
}

private class Day5Part2 : InputLines {

    override fun process(lines: List<String>): String {
        val separatorIndex = lines.indexOfFirst { it.isBlank() }
        val ranges = longRanges(lines, separatorIndex)
        val merged = mutableListOf<LongRange>()

        for (range in ranges.sortedBy { it.first }) {
            if (merged.isEmpty()) {
                merged.add(range)
                continue
            }

            merged.find { range.first >= it.first && range.last <= it.last }?.let {
                // already merged
                continue
            }

            merged.find { range.first < it.first && range.last >= it.first && range.last <= it.last }?.let {
                merged.remove(it)
                merged.add(range.first..it.last)
                continue
            }

            merged.find { range.first >= it.first && range.first <= it.last && range.last > it.last }?.let {
                merged.remove(it)
                merged.add(it.first..range.last)
                continue
            }

            merged.find { range.first < it.first && range.last > it.last }?.let {
                merged.remove(it)
                merged.add(range.first..range.last)
                continue
            }

            merged.add(range)
        }

        var sum = 0L

        for (range in merged) {
            sum += (range.last - range.first + 1)
        }

        return sum.toString()
    }
}

private class Day5Part1 : InputLines {

    override fun process(lines: List<String>): String {
        val separatorIndex = lines.indexOfFirst { it.isBlank() }
        val ranges = longRanges(lines, separatorIndex)
        val numbers = lines.subList(separatorIndex + 1, lines.size).map { it.toLong() }

        var sum = 0

        for (number in numbers) {
            if (ranges.any { number in it }) {
                sum += 1
            }
        }

        return sum.toString()
    }
}

private fun longRanges(lines: List<String>, separatorIndex: Int): List<LongRange> {
    val ranges = lines.subList(0, separatorIndex).map { line ->
        val (start, end) = line.split("-").map { it.toLong() }
        start..end
    }
    return ranges
}