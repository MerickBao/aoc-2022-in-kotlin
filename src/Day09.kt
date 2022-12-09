import kotlin.math.abs

fun main() {
    fun next(px: Int, py: Int, nx: Int, ny: Int): Array<Int> {
        val diff = abs(px - nx) + abs(py - ny)
        var x = nx
        var y = ny
        if (diff == 2) {
            if (px == nx) {
                if (py > ny) y++
                else y--
            } else if (py == ny) {
                if (px > nx) x++
                else x--
            }
        } else if (diff == 3) {
            if (abs(px - nx) == 2) {
                y = py
                if (px > nx) x++
                else x--
            } else {
                x = px
                if (py > ny) y++
                else y--
            }
        } else if (diff == 4) {
            if (py > ny) y++
            else y--
            if (px > nx) x++
            else x--
        }
        return arrayOf(x, y)
    }

    fun getDirection(s: String): Int {
        if (s == "U") return 0
        if (s == "D") return 1
        if (s == "L") return 2
        return 3
    }

    val dx = listOf(0, 0, -1, 1)
    val dy = listOf(1, -1, 0, 0)

    fun part(input: List<String>, size: Int): Int {
        val queue = Array(size) { Array(2) { 0 } }
        val all = HashSet<Int>()
        all.add(0)
        for (s in input) {
            val now = s.split(" ")
            val d = getDirection(now[0])
            var step = now[1].toInt()
            while (step-- > 0) {
                queue[0][0] += dx[d]
                queue[0][1] += dy[d]
                for (i in 1 until size) {
                    queue[i] = next(queue[i - 1][0], queue[i - 1][1], queue[i][0], queue[i][1])
                }
                all.add(queue[size - 1][0] * 100000 + queue[size - 1][1])
            }
        }
        return all.size
    }

    val input = readInput("Day09")
    println(part(input, 2))
    println(part(input, 10))
}