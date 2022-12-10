fun main() {
    fun ok(curr: Int, idx: Int): Boolean {
        return idx == curr || idx - 1 == curr || idx + 1 == curr
    }

    fun game(input: List<String>) {
        var ans = 0
        var curr = 1
        var idx = 0
        var cnt = 1
        for (s in input) {
            if (s == "noop") {
                if (cnt % 40 == 20) ans += curr * cnt
                cnt++
                if (ok(curr, idx)) print("#")
                else print(".")
                idx++
                if (idx == 40) {
                    idx = 0
                    println()
                }
            } else {
                val num = s.split(" ")[1].toInt()
                for (i in 0 until 2) {
                    if (cnt % 40 == 20) ans += curr * cnt
                    cnt++
                    if (ok(curr, idx)) print("#")
                    else print(".")
                    idx++
                    if (idx == 40) {
                        idx = 0
                        println()
                    }
                }
                curr += num
            }
        }
        println(ans)
    }

    val input = readInput("Day10")
    game(input)
}