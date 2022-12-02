fun main() {
    fun part1(input: List<String>): Int {
        var ans = 0
        var now = 0
        for (s in input) {
            if (s.equals("")) {
                ans = Math.max(ans, now)
                now = 0
            } else {
                now += s.toInt()
            }
        }
        return Math.max(ans, now)
    }

    fun part2(input: List<String>): Int {
        val all = mutableListOf<Int>()
        var now = 0
        for (s in input) {
            if (s.equals("")) {
                all.add(now)
                now = 0
            } else {
                now += s.toInt()
            }
        }
        all.sort()
        var ans = 0
        for (i in all.size - 1 downTo all.size - 3) {
            ans += all[i]
        }
        return ans
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 1)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
