import java.lang.IllegalStateException

class Day1 : Day {

    override fun asInt(): Int = 1

    override fun part1InputName(): String = "day1"

    override fun part1(input: List<String>): String {
        println("Day 1, Part 1")
        for (s in input) {
            for (t in input) {
                if (s.toInt() + t.toInt() == 2020) {
                    return (s.toInt() * t.toInt()).toString()
                }
            }
        }
        throw IllegalStateException("Could not find a pair that sums to 2020")
    }

    override fun part2InputName(): String = "day1"

    override fun part2(input: List<String>): String {
        println("Day 1, Part 2")
        for (s in input) {
            for (t in input) {
                for (v in input) {
                    if (s.toInt() + t.toInt() + v.toInt() == 2020) {
                        return (s.toInt() * t.toInt() * v.toInt()).toString()
                    }
                }
            }
        }
        throw IllegalStateException("Could not find a triplet that sums to 2020")
    }
}