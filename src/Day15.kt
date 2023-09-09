import kotlin.math.abs

fun main() {
    val beacon = HashSet<Long>()
    val base = 10000000000
    val all = mutableListOf<List<Long>>()

    fun count(input: String) {
        val xx = arrayListOf<Long>()
        val yy = arrayListOf<Long>()
        var idx = 0
        while (idx < input.length) {
            if (input[idx] == 'x') {
                var now = 0
                var f = 1
                idx += 2
                while (idx < input.length && (input[idx].isDigit() || input[idx] == '-')) {
                    if (input[idx] == '-') f = -1
                    else now = now * 10 + (input[idx] - '0')
                    idx++
                }
                xx.add((f * now).toLong())
            } else if (input[idx] == 'y') {
                var now = 0
                var f = 1
                idx += 2
                while (idx < input.length && (input[idx].isDigit() || input[idx] == '-')) {
                    if (input[idx] == '-') f = -1
                    else now = now * 10 + (input[idx] - '0')
                    idx++
                }
                yy.add((f * now).toLong())
            } else idx++
        }
        beacon.add(xx[1] * base + yy[1])
        all.add(listOf(xx[0], yy[0], xx[1], yy[1]))
    }

    fun part1(input: List<String>): Int {
        for (s in input) {
            count(s)
        }
        val ans = HashSet<Long>()
        val need = 2000000L
        for (cord in all) {
            val dist = abs(cord[0] - cord[2]) + abs(cord[1] - cord[3])
            //println(cord.toString())
            if (need in cord[1] - dist until cord[1] + dist + 1) {
                var x = cord[0]
                while (abs(x - cord[0]) + abs(need - cord[1]) <= dist) {
                    if (!beacon.contains(x * base + need)) {
                        ans.add(x)
                    }
                    x++
                }
                x = cord[0]
                while (abs(x - cord[0]) + abs(need - cord[1]) <= dist) {
                    if (!beacon.contains(x * base + need)) {
                        ans.add(x)
                    }
                    x--
                }
            }
        }
        //println(ans.toString())
        return ans.size
    }

    fun part2(input: List<String>): Int {

        for (cord in all) {
            val dist = abs(cord[0] - cord[2]) + abs(cord[1] - cord[3])
            //println("" + cord.toString() + " " + dist + " " + (abs(cord[0] - 14) + abs(cord[1] - 11)))
            println("" + cord[0] + "," + cord[1] + ":" + cord[2] + "," + cord[3])
        }
        return -1
    }

    val input = readInput("Day15")
    println(part1(input))
    println(part2(input))
}