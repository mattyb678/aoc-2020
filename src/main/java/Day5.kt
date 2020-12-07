import java.util.function.Predicate.not
import kotlin.math.floor
import kotlin.math.roundToInt

class Day5 : Day {
    override fun asInt(): Int = 5

    override fun part1InputName(): String = "day5"

    override fun part1(input: List<String>): String {
        println("Day 5, Part 1")
        return input.map { calcRow(it.take(7)) * 8 + calcCol(it.drop(7)) }.maxOrNull()?.toString().toString()
    }

    private fun calcCol(identifier: String): Int {
        return calc(identifier, 7, 'L')
    }

    private fun calcRow(rowIdentifier: String): Int {
        return calc(rowIdentifier, 127, 'F')
    }

    private fun calc(identifier: String, maxNum: Int, lowerChar: Char): Int {
        var max = maxNum
        var min = 0
        identifier.forEach {
            if (it == lowerChar) {
                max = floor((max - min) / 2.0).toInt() + min
            } else {
                min += ((max - min) / 2.0).roundToInt()
            }
        }
        return max
    }

    override fun part2InputName(): String = "day5"

    override fun part2(input: List<String>): String {
        println("Day 5, Part 2")
        val seatIds = input.map { calcRow(it.take(7)) * 8 + calcCol(it.drop(7)) }
            .sorted()
        val min = seatIds.first()
        val max = seatIds.last()
        return (min..max).filterNot { seatIds.contains(it) }.first().toString()
    }
}