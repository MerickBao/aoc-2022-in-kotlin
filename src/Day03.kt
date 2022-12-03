fun main() {

    fun computer1(s: String): Int {
        var cnt = 0
        val n = s.length
        val left = HashSet<Char>()
        val right = HashSet<Char>()
        for (i in 0 until n / 2) left.add(s[i])
        for (i in n / 2 until n) right.add(s[i])
        for (key in left) {
            if (right.contains(key)) {
                cnt += if (key in 'a'..'z') key - 'a' + 1
                else key - 'A' + 27
            }
        }
        return cnt
    }

    fun part1(input: List<String>): Int {
        var ans = 0
        for (s in input) ans += computer1(s)
        return ans
    }

    fun computer2(s1: String, s2: String, s3: String): Int {
        var cnt = 0
        val left = HashSet<Char>()
        val right = HashSet<Char>()
        val mid = HashSet<Char>()
        for (i in s1) left.add(i)
        for (i in s2) mid.add(i)
        for (i in s3) right.add(i)
        for (key in left) {
            if (mid.contains(key) && right.contains(key)) {
                cnt += if (key in 'a'..'z') key - 'a' + 1
                else key - 'A' + 27
            }
        }
        return cnt
    }

    fun part2(input: List<String>): Int {
        var ans = 0
        for (i in input.indices step 3) {
            ans += computer2(input[i], input[i + 1], input[i + 2])
        }
        return ans
    }

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
