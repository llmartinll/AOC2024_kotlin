fun main() {
    val day = 3
    var doMode = true

    fun toPair(input: String): Pair<Int, Int> {
        val ints = input.removePrefix("mul(").removeSuffix(")").split(',').map{it.toInt()}
        return Pair(ints.first(), ints[1])
    }

    fun scanMuls(input: String): List<Pair<Int,Int>> {
        val regex = Regex("""mul\(\d+,\d+\)""")
        return regex.findAll(input).toList().map{it.value}.map(::toPair)
    }

    fun scanMuls2(input: String): List<Pair<Int,Int>> {
        if(input.startsWith("don't()")) {
            doMode = false
            return scanMuls2(input.substringAfter("don't()"))
        } else if(input.startsWith("do()")) {
            doMode = true
            return scanMuls2(input.substringAfter("do()"))
        }

        val regex = Regex("""^mul\(\d+,\d+\)""")
        if(regex.containsMatchIn(input)) {
            val restString = regex.replace(input, "")
            if (doMode) {
                val pair = toPair(regex.find(input)!!.value)
                return listOf(pair) + scanMuls2(restString)
            } else {
                return scanMuls2(restString)
            }
        }

        if(input.count() == 0) {
            return emptyList()
        }
        return scanMuls2(input.removeRange((0..0)))
    }


    fun part1(input: List<String>): Int {
        return input.sumOf {
            scanMuls(it).sumOf { it.first * it.second }
        }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf {
            scanMuls2(it).sumOf { it.first * it.second }
        }
    }


    val testInput = readInput("Day0${day}_test")
    part1(testInput).println()
    part2(testInput).println()

    val input = readInput("Day0${day}")
    part1(input).println()
    part2(input).println()
}
