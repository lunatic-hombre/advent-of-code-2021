import java.lang.RuntimeException

fun main() {
    fun part1(bitSize: Int, input: List<Int>): Int {
        var epsilon = 0
        for (bitIndex in 0..bitSize) {
            val bitAtIndex = 1 shl (bitSize - bitIndex)
            var count = 0
            for (i in input) {
                if (i and bitAtIndex != 0)
                    count++
                if (count >= input.size / 2) {
                    epsilon = epsilon or bitAtIndex
                    break
                }
            }
        }
        val ones = "1".repeat(bitSize).toInt(2)
        val gamma = epsilon.inv() and ones
        println("epsilon ${epsilon.toString(2)} x gamma ${gamma.toString(2)}")
        return epsilon * gamma
    }

    fun part2(bitSize: Int, input: List<Int>): Int {
        val o2 = findMatch(bitSize, input) {
            it >= 0
        }
        val co2 = findMatch(bitSize, input) {
            it < 0
        }
        println("O2 $o2 x CO2 $co2")
        return o2 * co2
    }

    // test if implementation meets criteria from the description, like:
    val (len, data) = readInputBinaries("Day03_test")
    val result = part2(len, data)
    check(result == 230)

    val (len1, data1) = readInputBinaries("Day03")
    println(part1(len1, data1))

    val (len2, data2) = readInputBinaries("Day03")
    println(part2(len2, data2))
}

fun readInputBinaries(file: String): Pair<Int, List<Int>> {
    val strInputs = readInput(file)
    return strInputs[0].length to strInputs.map {
        it.toInt(2)
    }
}

fun findMatch(bitSize: Int, input: List<Int>, criteria: (Int) -> Boolean): Int {
    var matching = ArrayList(input)
    for (bitIndex in 0..bitSize) {
        val bitAtIndex = 1 shl (bitSize - bitIndex - 1)
        val ones = ArrayList<Int>()
        val zeroes = ArrayList<Int>()
        var count = 0
        for (i in matching) {
            if (i and bitAtIndex != 0) {
                count++
                ones.add(i)
            } else {
                count--
                zeroes.add(i)
            }
        }
        matching = if (criteria(count)) ones else zeroes

        if (matching.size == 1)
            return matching[0]
    }
    throw RuntimeException("Could not find single match")
}