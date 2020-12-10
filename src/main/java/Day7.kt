class Day7 : Day {

    companion object {
        private val parentRegex = "([a-z]+\\s[a-z]+)".toRegex()
        private val contentRegex = "((\\d)\\s([a-z]+\\s[a-z]+))+".toRegex()
    }

    override fun asInt(): Int = 7

    override fun part1InputName(): String = "day7"

    override fun part1(input: List<String>): String {
        val bagMap = input.toBagMap()
        fun containsTarget(target: String, current: String): Boolean {
            val contents = bagMap[current] ?: emptyList()
            return when {
                contents.isEmpty() -> false
                contents.map { it.color }.any { it == target } -> true
                else -> {
                    contents.map { containsTarget(target, it.color) }.any { it }
                }
            }
        }
        return bagMap.keys.filter { containsTarget("shiny gold", it) }
            .count()
            .toString()
    }

    override fun part2InputName(): String = "day7"

    override fun part2(input: List<String>): String {
        val bagMap = input.toBagMap()
        fun bagsContainedIn(target: String):Int = bagMap[target]!!.sumBy { bag ->
                bag.count + (bag.count * bagsContainedIn(bag.color))
        }
        return bagsContainedIn("shiny gold").toString()
    }

    private fun List<String>.toBagMap(): Map<String, List<BagContent>> {
        return this.associate {
            val parts = it.split("contain")
            val parent = parentRegex.find(parts[0])?.groupValues?.get(0) ?: ""
            val contents = contentRegex.findAll(parts[1])
                .map { content -> content.destructured  }
                .map { (_, count, color) -> BagContent(color, count.toInt()) }
                .toList()
            parent to contents
        }
    }
}


data class BagContent(val color: String, val count: Int)