import java.util.LinkedList

fun main() {

    val supply = arrayOf("", "BWN", "LZSPTDMB", "QHZWR", "WDVJZR", "SHMB", "LGNJHVPB", "JQZFHDLS", "WSFJGQB", "ZWMSCDJ")

    fun part1(input: List<String>): String {
        val stacks = Array(10) {LinkedList<Char>()}
        for (i in supply.indices) {
            for (j in supply[i]) {
                stacks[i].offerLast(j)
            }
        }
        for (i in 10 until input.size) {
            val now = input[i].split(" ")
            var cnt = now[1].toInt()
            val from = now[3].toInt()
            val to = now[5].toInt()
            while (cnt-- > 0) {
                stacks[to].offerLast(stacks[from].pollLast())
            }
        }
        val ans = StringBuilder()
        for (i in 1 until stacks.size) {
            ans.append(if (stacks[i].isEmpty()) " " else stacks[i].peekLast())
        }
        return ans.toString()
    }

    fun part2(input: List<String>): String {
        val stacks = Array(10) {LinkedList<Char>()}
        for (i in supply.indices) {
            for (j in supply[i]) {
                stacks[i].offerLast(j)
            }
        }
        for (i in 10 until input.size) {
            val now = input[i].split(" ")
            var cnt = now[1].toInt()
            val from = now[3].toInt()
            val to = now[5].toInt()
            val tmp = LinkedList<Char>()
            while (cnt-- > 0) {
                tmp.offerFirst(stacks[from].pollLast())
            }
            while (!tmp.isEmpty()) {
                stacks[to].offerLast(tmp.pollFirst())
            }
        }
        val ans = StringBuilder()
        for (i in 1 until stacks.size) {
            ans.append(if (stacks[i].isEmpty()) " " else stacks[i].peekLast())
        }
        return ans.toString()
    }

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
