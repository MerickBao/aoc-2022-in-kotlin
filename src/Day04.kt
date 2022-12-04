fun main() {

    fun game1(s: String): Boolean {
        val arr = s.split(",")
        val L = arr[0].split("-")
        val R = arr[1].split("-")
        val ll = L[0].toInt()
        val lr = L[1].toInt()
        val rl = R[0].toInt()
        val rr = R[1].toInt()
        return ll >= rl && lr <= rr || rl >= ll && rr <= lr
    }

    fun game2(s: String): Boolean {
        val arr = s.split(",")
        val L = arr[0].split("-")
        val R = arr[1].split("-")
        val ll = L[0].toInt()
        val lr = L[1].toInt()
        val rl = R[0].toInt()
        val rr = R[1].toInt()
        return ll in rl..rr || lr in rl..rr || rl in ll..lr || rr in ll..lr
    }

    fun part1(input: List<String>): Int {
        var ans = 0
        for (s in input) {
            if (game1(s)) ans++
        }
        return ans
    }

    fun part2(input: List<String>): Int {
        var ans = 0
        for (s in input) {
            if (game2(s)) ans++
        }
        return ans
    }

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
