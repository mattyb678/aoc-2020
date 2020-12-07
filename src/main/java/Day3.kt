class Day3 : Day {

    override fun asInt(): Int = 3

    override fun part1InputName(): String = "day3"

    override fun part1(input: List<String>): String {
        println("Day 3, Part 1")
        val treeCount = treeCountForSlope(3, 1, input)
        return treeCount.toString()
    }

    private fun treeCountForSlope(x: Int, y: Int, rows: List<String>): Int {
        var rowIdx = 0 + y
        var colIdx = 0
        var treeCount = 0
        while (rowIdx < rows.size) {
            colIdx += x
            val row = rows[rowIdx]
            val toCheck = colIdx % row.count()
            if (row[toCheck] == TREE) {
                treeCount += 1
            }
            rowIdx += y
        }
        return treeCount
    }

    override fun part2InputName(): String = "day3"

    override fun part2(input: List<String>): String {
        val counts = listOf(
            treeCountForSlope(1, 1, input),
            treeCountForSlope(3, 1, input),
            treeCountForSlope(5, 1, input),
            treeCountForSlope(7, 1, input),
            treeCountForSlope(1, 2, input)
        )
        return counts.map { it.toDouble() }.reduce(Double::times).toBigDecimal().toPlainString()
    }

    companion object {
        private const val TREE = '#'
    }
}