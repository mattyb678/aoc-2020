class Day2 : Day {
    override fun asInt(): Int = 2

    override fun part1InputName(): String = "day2"

    override fun part1(input: List<String>): String {
        return input.map { it.split(":") }
            .map { it.map { s -> s.trim() } }
            .filter { validPasswordPart1(it[0], it[1]) }
            .count()
            .toString()
    }

    private fun validPasswordPart1(rule: String, password: String): Boolean {
        val letterAndLimits = rule.split(" ").map { it.trim() }
        val limits = letterAndLimits[0]
            .split("-")
            .map { it.toInt() }
            .chunked(2)
            .map { it[0]..it[1] }
            .first()
        val letter = letterAndLimits[1][0]
        val times = password.filter { it == letter }.count()
        return times in limits
    }

    override fun part2InputName(): String = "day2"

    override fun part2(input: List<String>): String {
        return input.map { it.split(":") }
            .map { it.map { s -> s.trim() } }
            .filter { validPasswordPart2(it[0], it[1]) }
            .count()
            .toString()
    }

    private fun validPasswordPart2(rule: String, password: String): Boolean {
        val letterAndPositions = rule.split(" ").map { it.trim() }
        val positions = letterAndPositions[0]
            .split("-")
            .map { it.toInt() }
            .chunked(2)
            .map { it[0] to it[1] }
            .first()
        val letter = letterAndPositions[1][0]
        val matchCount = password
            .filterIndexed { idx, _ -> idx == positions.first - 1 || idx == positions.second - 1 }
            .filter { it == letter }
            .count()
        return matchCount == 1
    }
}