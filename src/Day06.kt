fun main() {
    fun game(input: String, windowSize: Int): Int {
        val all = HashSet<Char>()
        for (i in windowSize - 1 until input.length) {
            for (j in i downTo i - windowSize + 1) {
                all.add(input[j])
            }
            if (all.size == windowSize) return i + 1
            all.clear()
        }
        return -1
    }

    fun part1(input: String): Int {
        return game(input, 4)
    }

    fun part2(input: String): Int {
        return game(input, 14)
    }

    val input = readInput("Day06")
    println(part1(input[0]))
    println(part2(input[0]))
}
