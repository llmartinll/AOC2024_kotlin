import kotlin.math.absoluteValue

fun main() {
    val day = 2

    fun isSafe(numbers: List<Int>): Boolean {
        if (numbers != numbers.sorted() && numbers != numbers.sortedDescending()) {
            return false
        }
        numbers.forEachIndexed { index, number ->
            if (index == numbers.lastIndex) {
                return@forEachIndexed
            }
            val next = numbers[index + 1]
            val diff = (number - next).absoluteValue
            if (diff < 1 || diff > 3) {
                return false
            }
        }
        return true
    }

    fun isSafe2(numbers: List<Int>): Boolean {
        val variations = listOf(numbers) +
                (0..numbers.lastIndex).map { removeIndex ->
                    numbers.filterIndexed { index, _ -> index != removeIndex }
                }
        return variations.any(::isSafe)
    }

    fun part1(input: List<String>): Int {
        return input.map(::toIntList).count(::isSafe)
    }

    fun part2(input: List<String>): Int {
        return input.map(::toIntList).count(::isSafe2)
    }


    val testInput = readInput("Day0${day}_test")
    part1(testInput).println()
    part2(testInput).println()

    val input = readInput("Day0${day}")
    part1(input).println()
    part2(input).println()
}
