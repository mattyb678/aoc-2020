class Day9 : Day {
    override fun asInt(): Int = 9

    override fun part1InputName(): String = "day9"

    override fun part1(input: List<String>): String {
        val preamble = 25
        return firstNonMatch(preamble, input).toString()
    }

    private fun List<Long>.sums(): Sequence<Long> {
        val result = mutableListOf<Long>()
        this.forEachIndexed { i, x ->
            this.forEachIndexed { j, y ->
                if (i != j) {
                    result.add(x + y)
                }
            }
        }
        return result.asSequence()
    }

    override fun part2InputName(): String = "day9"

    override fun part2(input: List<String>): String {
        val preamble = 25
        val invalidNumber = firstNonMatch(preamble, input)
        val values = input.map(String::toLong)
        val prefixSums = values.prefixSum()
        for (i in 0 until prefixSums.size) {
            for (j in 1 until prefixSums.size) {
                val begin = prefixSums[i]
                val end = prefixSums[j]
                if (end - begin == invalidNumber) {
                    val inner = values.subList(i, j)
                    val min = inner.minOrNull() ?: Long.MIN_VALUE
                    val max = inner.maxOrNull() ?: Long.MAX_VALUE
                    return (min + max).toString()
                }
            }
        }
        return "-1"
    }

    private fun List<Long>.prefixSum(): List<Long> {
        return this.fold(listOf()) { resultList, el ->
            if (resultList.isEmpty()) {
                resultList + el
            } else {
                resultList + (resultList.last() + el)
            }
        }
    }

    private fun firstNonMatch(preambleCount: Int, input: List<String>): Long {
        val numberList = input.map(String::toLong).windowed(preambleCount + 1, 1)
        var firstNonMatch = 0L
        for (numbers in numberList) {
            val last = numbers.last()
            val toCheck = numbers.dropLast(1)
            val match = toCheck.sums().any { it == last }
            if (!match) {
                firstNonMatch = last
                break
            }
        }
        return firstNonMatch
    }
}