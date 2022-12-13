import java.util.LinkedList

fun main() {
    fun compare(a: String, b: String): Int {
        val mapA = HashMap<Int, Int>()
        val mapB = HashMap<Int, Int>()
        val q = LinkedList<Int>()
        for (i in a.indices) {
            if (a[i] == '[') q.offerLast(i)
            else if (a[i] == ']') mapA[q.pollLast()] = i
        }
        for (i in b.indices) {
            if (b[i] == '[') q.offerLast(i)
            else if (b[i] == ']') mapB[q.pollLast()] = i
        }
        var idx1 = 0
        var idx2 = 0
        while (idx1 < a.length && idx2 < b.length) {
            if (a[idx1] == '[') {
                if (b[idx2] == '[') {
                   // println("" + a.length + " " + b.length + " " + idx1 + " " + idx2)
                    val cmp = compare(a.substring(idx1 + 1, mapA[idx1]!!), b.substring(idx2 + 1, mapB[idx2]!!))
                    if (cmp == 0) {
                        idx1 = mapA[idx1]!! + 2
                        idx2 = mapB[idx2]!! + 2
                    } else return cmp
                } else {
                    val idx = idx2
                    while (idx2 < b.length && b[idx2].isDigit()) idx2++
                    val cmp = compare(a.substring(idx1 + 1, mapA[idx1]!!), b.substring(idx, idx2))
                    if (cmp == 0) {
                        idx1 = mapA[idx1]!! + 2
                        idx2++
                    } else return cmp
                }
            } else {
                if (b[idx2] == '[') {
                    val idx = idx1
                    while (idx1 < a.length && a[idx1].isDigit()) idx1++
                    val cmp = compare(a.substring(idx, idx1), b.substring(idx2 + 1, mapB[idx2]!!))
                    if (cmp == 0) {
                        idx1++
                        idx2 = mapB[idx2]!! + 2
                    } else return cmp
                } else {
                    var numa = 0
                    var numb = 0
                    while (idx1 < a.length && a[idx1].isDigit()) numa = numa * 10 + (a[idx1++] - '0')
                    while (idx2 < b.length && b[idx2].isDigit()) numb = numb * 10 + (b[idx2++] - '0')
                    if (numa < numb) return 1
                    else if (numa > numb) return -1
                    idx1++
                    idx2++
                }
            }
        }
        if (idx1 >= a.length) {
            if (idx2 >= b.length) return 0
            return 1
        }
        return -1
    }

    fun game(input: List<String>) {
        val all = arrayListOf<String>()
        for (s in input) {
            if (s == "") continue
            all.add(s)
        }
        var cnt = 0
        var idx = 0
        while (idx < all.size) {
            if (compare(all[idx], all[idx + 1]) == 1) cnt += idx / 2 + 1
            idx += 2
        }
        println(cnt)
        all.add("[[2]]")
        all.add("[[6]]")
        all.sortWith { o1: String, o2: String -> compare(o2, o1) }
        println((all.indexOf("[[2]]") + 1) * (all.indexOf("[[6]]") + 1))
    }

    val input = readInput("Day13")
    game(input)
}