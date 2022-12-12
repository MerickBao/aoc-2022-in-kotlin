import java.util.LinkedList
import kotlin.math.min

fun main() {
    fun bfs(grid: Array<CharArray>, sx: Int, sy: Int, ex: Int, ey: Int): Int {
        var ans = 0
        val n = grid.size
        val m = grid[0].size
        val seen = Array(n){ BooleanArray(m) }
        seen[sx][sy] = true
        val dx = listOf(-1, 1, 0, 0)
        val dy = listOf(0, 0, -1, 1)

        val q = LinkedList<List<Int>>()
        q.offer(listOf(sx, sy))

        while (!q.isEmpty()) {
            var size = q.size
            while (size-- > 0) {
                val now = q.poll()
                for (i in 0 until 4) {
                    val x = now[0] + dx[i]
                    val y = now[1] + dy[i]
                    if (x < 0 || x >= n || y < 0 || y >= m) continue
                    if (seen[x][y] || grid[x][y] - grid[now[0]][now[1]] > 1) continue
                    if (x == ex && y == ey) return ans + 1
                    seen[x][y] = true
                    q.offer(listOf(x, y))
                }
            }
            ans++
        }
        return 1000000000
    }

    fun game(input: List<String>) {
        var sx = 0
        var sy = 0
        var ex = 0
        var ey = 0
        val starters: ArrayList<List<Int>> = arrayListOf()
        val grid = Array(input.size) { CharArray(input[0].length) }
        for (i in input.indices) {
            grid[i] = input[i].toCharArray()
            for (j in 0 until input[0].length) {
                if (input[i][j] == 'S') {
                    sx = i
                    sy = j
                    grid[i][j] = 'a'
                }
                if (input[i][j] == 'E') {
                    ex = i
                    ey = j
                    grid[i][j] = 'z'
                }
                if (grid[i][j] == 'a') starters.add(listOf(i, j))
            }
        }
        println(bfs(grid, sx, sy, ex, ey))
        var ans = 1000000000
        for (start in starters) {
            ans = min(ans, bfs(grid, start[0], start[1], ex, ey))
        }
        println(ans)
    }

    val input = readInput("Day12")
    game(input)
}