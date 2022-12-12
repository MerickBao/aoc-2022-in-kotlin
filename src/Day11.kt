import java.math.BigInteger

fun main() {

    var LCM = 1L

    fun gcd(a: Long, b: Long): Long {
        if (b == 0L) return a
        return gcd(b, a % b)
    }

    fun lcm(a: Long, b: Long): Long {
        return a * b / gcd(a, b)
    }

    class Node {
        var id: Int = 0
        var items= arrayListOf<BigInteger>()
        var op: String = ""
        var factor: Long = 0
        var test: Long = 0
        var trueId: Int = 0
        var falseId: Int = 0
    }

    fun next(op: String, x: BigInteger, y: BigInteger): BigInteger {
        if (op == "+") return x.add(y)
        if (op == "-") return x.subtract(y)
        if (op == "*") return x.multiply(y)
        return x / y
    }

    fun game(input: List<String>): Long {
        val monkey = arrayListOf<Node>()
        var idx = 0
        while (idx < input.size) {
            val node = Node()
            node.id = idx / 7

            var now = input[idx + 1].trim().split(" ")
            val items = arrayListOf<BigInteger>()
            for (i in 2 until now.size) {
                if (i == now.size - 1) items.add(BigInteger(now[i]))
                else items.add(BigInteger(now[i].substring(0, now[i].length - 1)))
            }
            node.items = items

            now = input[idx + 2].trim().split(" ")
            node.op = now[4]

            if (now[5] == "old") node.factor = -1
            else node.factor = now[5].toLong()

            now = input[idx + 3].trim().split(" ")
            node.test = now[3].toLong()
            LCM = lcm(LCM, now[3].toLong())

            now = input[idx + 4].trim().split(" ")
            node.trueId = now[5].toInt()

            now = input[idx + 5].trim().split(" ")
            node.falseId = now[5].toInt()

            monkey.add(node)
            idx += 7
        }

        val cnt = LongArray(monkey.size)
        for (x in 0 until 10000) {
            for (i in monkey.indices) {
                cnt[i] += 1L * monkey[i].items.size
                for (item in monkey[i].items) {
                    var old = item
                    if (monkey[i].factor == -1L) old = next(monkey[i].op, old, old)
                    else old = next(monkey[i].op, old, BigInteger("" + monkey[i].factor))

                    // old /= 3
                    old = old.mod(BigInteger("" + LCM))

                    val nextId: Int = if (old.mod(BigInteger("" + monkey[i].test)).toString() == "0") {
                        monkey[i].trueId
                    } else {
                        monkey[i].falseId
                    }

                    monkey[nextId].items.add(old)
                }
                monkey[i].items.clear()
            }
        }

        for (i in cnt) print("$i ")
        println()
        cnt.sort()
        return cnt[monkey.size - 1] * cnt[monkey.size - 2]
    }

    val input = readInput("Day11")
    println(game(input))
}