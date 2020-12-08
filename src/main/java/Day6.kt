class Day6 : Day {
    override fun asInt(): Int = 6

    override fun part1InputName(): String = "day6"

    override fun part1(input: List<String>): String {
        println("Day 6, Part 1")
        return input.group()
            .map { it.flatMap { str -> str.toList() }.distinct().count() }
            .sum()
            .toString()
    }

    override fun part2InputName(): String = "day6"

    override fun part2(input: List<String>): String {
        println("Day 6, Part 1")
        return input.group()
            .map { it.map { str -> str.toList() } }
            .map { it.reduce { acc, list -> acc.intersect(list).toList() }.count() }
            .sum()
            .toString()
    }
}
