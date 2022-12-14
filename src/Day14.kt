import kotlin.math.max
import kotlin.math.min

fun main() {
    var MAX = 0

    fun build(input: List<String>): HashSet<Int> {
        val res = HashSet<Int>()
        for (s in input) {
            val coords = s.split(" -> ")
            for (i in coords.indices) {
                if (i == 0) continue
                val prev = coords[i - 1].split(",")
                val now = coords[i].split(",")
                val px = prev[0].toInt()
                val py = prev[1].toInt()
                val nx = now[0].toInt()
                val ny = now[1].toInt()
                MAX = max(MAX, max(py, ny))
                if (px == nx) {
                    for (y in min(py, ny) until  max(py, ny) + 1) {
                        res.add(px * 100000000 + y)
                    }
                } else {
                    for (x in min(px, nx) until max(px, nx) + 1) {
                        res.add(x * 100000000 + py)
                    }
                }
            }
        }
        return res
    }

    fun check(all: HashSet<Int>, isP2: Boolean, maxY: Int): Boolean {
        var x = 500
        var y = 0
        while (y <= maxY && !all.contains(x * 100000000 + y)) {
            if (isP2 && y + 1 >= maxY) {
                all.add(x * 100000000 + y)
                return true
            }
            if (!all.contains(x * 100000000 + y + 1)) {
                y++
            } else if (!all.contains((x - 1) * 100000000 + y + 1)) {
                x--
                y++
            } else if (!all.contains((x + 1) * 100000000 + y + 1)) {
                x++
                y++
            } else {
                all.add(x * 100000000 + y)
                return true
            }
        }
        return false
    }

    fun part1(input: List<String>): Int {
        val all = build(input)
        var ans = 0
        while (check(all, false, MAX)) ans++
        return ans
    }

    fun part2(input: List<String>): Int {
        val all = build(input)
        var ans = 0
        while (check(all, true, MAX + 2)) ans++
        return ans
    }

    val input = readInput("Day14")
    println(part1(input))
    println(part2(input))
}