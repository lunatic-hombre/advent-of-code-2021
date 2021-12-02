fun main() {
    fun part1(input: List<Pair<String, Int>>): Int {
        var distance = 0
        var depth = 0
        for ((action, value) in input)
            when (action) {
                "up" -> depth -= value
                "down" -> depth += value
                "forward" -> distance += value
            }
        return distance * depth
    }

    fun part2(input: List<Pair<String, Int>>): Int {
        var distance = 0
        var depth = 0
        var aim = 0
        for ((action, value) in input)
            when (action) {
                "up" -> aim -= value
                "down" -> aim += value
                "forward" -> {
                    distance += value
                    depth += aim * value
                }
            }
        return distance * depth
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputCommands("Day02_test")
    val part2 = part2(testInput)
    check(part2 == 900)

    val input = readInputCommands("Day02")
    println(part1(input))
    println(part2(input))
}

fun readInputCommands(file: String) = readInput(file).map {
    val (action, value) = it.split(" ")
    Pair(action, value.toInt())
}
