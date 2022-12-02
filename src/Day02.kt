fun main() {
    fun game1(s: String): Int {
        return if (s[2] == 'X') {
            if (s[0] == 'A') 1 + 3
            else if (s[0] == 'B') 1
            else 1 + 6
        } else if (s[2] == 'Y') {
            if (s[0] == 'A') 2 + 6
            else if (s[0] == 'B') 2 + 3
            else 2
        } else {
            if (s[0] == 'A') 3
            else if (s[0] == 'B') 3 + 6
            else 3 + 3
        }
    }

    fun game2(s: String): Int {
        return if (s[2] == 'X') { // lose
            if (s[0] == 'A') 3
            else if (s[0] == 'B') 1
            else 2
        } else if (s[2] == 'Y') { // draw
            if (s[0] == 'A') 1 + 3
            else if (s[0] == 'B') 2 + 3
            else 3 + 3
        } else { // win
            if (s[0] == 'A') 2 + 6
            else if (s[0] == 'B') 3 + 6
            else 1 + 6
        }
    }

    fun part1(input: List<String>): Int {
        var ans = 0
        for (s in input) {
            ans += game1(s)
        }
        return ans
    }

    fun part2(input: List<String>): Int {
        var ans = 0
        for (s in input) {
            ans += game2(s)
        }
        return ans
    }

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
