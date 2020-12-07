class Day4 : Day {

    companion object {
        private val mandatoryFields = mapOf(
            "byr" to { it.toInt() in 1920..2002 },
            "iyr" to { it.toInt() in 2010..2020 },
            "eyr" to { it.toInt() in 2020..2030 },
            "hgt" to heightValidator,
            "hcl" to { "^#[0-9a-f]{6}\$".toRegex().matches(it) },
            "ecl" to { it in listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth") },
            "pid" to { "^[0-9]{9}\$".toRegex().matches(it) }
        )
    }

    override fun asInt(): Int = 4

    private fun List<String>.toPassports(): Sequence<Map<String, String>> {
        return this.mapIndexedNotNull { i, s -> i.takeIf { s == "" } }
            .let { listOf(-1) + it + this.size }
            .asSequence()
            .zipWithNext { from, upto -> this.subList(from + 1, upto) }
            .map { it.joinToString(" ") }
            .map { it.split(" ") }
            .map { it.map { s -> s.split(":") }.associate { list -> list[0] to list[1] } }
    }

    override fun part1InputName(): String = "day4"

    override fun part1(input: List<String>): String {
        println("Day 4, Part 1")
        return input.toPassports()
            .filter(::validPart1)
            .count()
            .toString()
    }

    private fun validPart1(fields: Map<String, String>): Boolean {
        return fields.keys.containsAll(mandatoryFields.keys)
    }

    override fun part2InputName(): String = "day4"

    override fun part2(input: List<String>): String {
        println("Day 4, Part 2")
        return input.toPassports()
            .filter(::validPart2)
            .count()
            .toString()
    }

    private fun validPart2(fields: Map<String, String>): Boolean {
        return fields.keys.containsAll(mandatoryFields.keys) &&
                mandatoryFields.keys.all { fields[it]?.let { it1 -> mandatoryFields[it]?.invoke(it1) } == true }
    }
}

typealias Validator = (String) -> Boolean

val heightValidator: Validator = {
    when {
        it.endsWith("cm") -> {
            it.replace("cm", "").toInt() in 150..193
        }
        it.endsWith("in") -> {
            it.replace("in", "").toInt() in 59..76
        }
        else -> {
            false
        }
    }
}