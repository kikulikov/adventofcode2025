package org.example

import org.example.InputLines.Companion.fromFileAsString

/**
 * https://adventofcode.com/2025/day/2#part2
 */
private class Day2Part2 : InputLines {

    override fun process(lines: List<String>): String {
        var sum = 0L

        for (line in lines) {
            val left = line.substringBefore("-").toLong()
            val right = line.substringAfter("-").toLong()

            for (num in left..right) {
                val str = num.toString()

                for (n in 1..str.length / 2) {
                    val part = str.take(n)

                    if (part.repeat(str.length / n) == str) {
                        sum += num
                        // println("Found repeating number: $num")
                        break
                    }
                }
            }
        }
        return sum.toString()
    }
}

private const val dataSample = "11-22,95-115,998-1012,1188511880-1188511890," +
        "222220-222224,1698522-1698528,446443-446449,38593856-38593862," +
        "565653-565659,824824821-824824827,2121212118-2121212124"

private const val inputFile = "app/src/main/resources/day2.txt"

fun main() {
    println(
        "Answer: " + Day2Part2().process(dataSample.split(","))
    )
    println(
        "Answer: " + Day2Part2().process(fromFileAsString(inputFile).split(","))
    )
}
