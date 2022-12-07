fun main() {
    // build a file tree
    fun build(input: List<String>): Node {
        val root = Node("/")
        var curr: Node = root
        val parent = HashMap<Node, Node>()
        var i = 0
        while (i < input.size) {
            var now = input[i].split(" ")
            if (now[1] == "cd") { // cd dir
                if (now[2] == "/") curr = root // cd /
                else if (now[2] == "..") curr = parent[curr]!! // cd ..
                else curr = curr.child[now[2]]!! // cd subDir
            } else {
                // show the file of the dir
                var j = i + 1
                while (j < input.size && input[j][0] != '$') {
                    now = input[j].split(" ")
                    if (now[0] == "dir") {
                        val dir = Node(now[1])
                        dir.isDir = true
                        parent[dir] = curr
                        curr.child[now[1]] = dir
                    } else {
                        val file = Node(now[1])
                        file.size = now[0].toInt()
                        parent[file] = curr
                        curr.child[now[1]] = file
                    }
                    j++
                }
                i = j - 1
            }
            i++
        }
        return root
    }

    var tot = 0
    var minDeletedSpace = Int.MAX_VALUE

    fun dfs(root: Node, limit: Int, need: Int): Int {
        var cnt = root.size
        for (next in root.child.values) {
            cnt += dfs(next, limit, need)
        }
        if (root.isDir && cnt <= limit) {
            tot += cnt
        }
        if (root.isDir && cnt >= need) {
            minDeletedSpace = Math.min(minDeletedSpace, cnt)
        }
        return cnt
    }

    fun part(input: List<String>) {
        val root = build(input)
        // in my input:
        // total used space = 48008081
        // left space = 70000000 - 48008081 = 21991919
        // in order to get space 30000000, we need delete used space at least 30000000 - 21991919 = 8008081
        dfs(root, 100000, 8008081)
        println(tot)
        println(minDeletedSpace)
    }

    val input = readInput("Day07")
    part(input)
}

class Node(var name: String) {
    var size: Int = 0
    var isDir: Boolean = false
    var child: HashMap<String, Node> = HashMap()
}