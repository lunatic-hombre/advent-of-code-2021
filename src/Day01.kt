fun main() {
    fun part1(input: List<Int>): Int {
        return (0..input.size-2).asSequence().filter { i ->
            input[i] < input[i+1]
        }.count()
    }

    fun part2(input: List<Int>): Int {
        return part1((0..input.size-3).map { i ->
            input[i] + input[i+1] + input[i+2]
        })
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputNumbers("Day01_test")
    check(part1(testInput) == 7)

    val input = readInputNumbers("Day01")
    println(part1(input))
    println(part2(input))
}
