import java.util.LinkedList
import javax.rmi.CORBA.Util

fun main() {
    fun part1(input: List<String>): Int {
        val n = input.size
        val m = input[0].length
        var ans = 2 * (n + m - 2)
        val dx = listOf(1, -1, 0, 0)
        val dy = listOf(0, 0, -1, 1)

        for (i in 1 until n - 1) {
            for (j in 1 until m - 1) {
                for (k in 0 until 4) {
                    var ok = true
                    var x = i + dx[k]
                    var y = j + dy[k]
                    while (x >= 0 && x < n && y >= 0 && y < m) {
                        if (input[x][y] >= input[i][j]) {
                            ok = false
                            break
                        }
                        x += dx[k]
                        y += dy[k]
                    }
                    if (ok) {
                        ans++
                        break
                    }
                }
            }
        }
        return ans
    }

    fun part2(input: List<String>): Int {
        val n = input.size
        val m = input[0].length
        var ans = 0
        val dx = listOf(1, -1, 0, 0)
        val dy = listOf(0, 0, -1, 1)

        for (i in 1 until n - 1) {
            for (j in 1 until m - 1) {
                var now = 1
                for (k in 0 until 4) {
                    var x = i + dx[k]
                    var y = j + dy[k]
                    var cnt = 0
                    while (x >= 0 && x < n && y >= 0 && y < m) {
                        cnt++
                        if (input[x][y] >= input[i][j]) {
                            break
                        }
                        x += dx[k]
                        y += dy[k]
                    }
                    now *= cnt
                }
                ans = Math.max(ans, now)
            }
        }
        return ans
    }

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}