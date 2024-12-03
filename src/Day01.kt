fun main() {
    fun part1(input: List<String>): Int {
        val leftList = input.map { it.split(' ').first().toInt() }.sorted()
        val rightList = input.map { it.split(' ').last().toInt() }.sorted()

        return leftList.mapIndexed { index, value ->
            val other = rightList[index]
            if (value > other) value - other else other - value
        }.sum()
    }

    fun part2(input: List<String>): Int {
        val leftList = input.map { it.split(' ').first().toInt() }.sorted()
        val rightList = input.map { it.split(' ').last().toInt() }.sorted()

        return leftList.sumOf { value ->
            value * rightList.count { it == value }
        }
    }

    val testInput = readInput("Day01_test")
    part1(testInput).println()
    part2(testInput).println()

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
