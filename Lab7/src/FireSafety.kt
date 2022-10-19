fun main() {
    val n = readln().toInt()
    val m = readln().toInt()
    val outE = Array(n + 1) { ArrayList<Int>() }
    val inE = Array(n + 1) { ArrayList<Int>() }

    repeat(m) {
        val (from, to) = readln().split(" ").map { it.toInt() }
        outE[from].add(to)
        inE[to].add(from)
    }

    val marked = BooleanArray(n + 1)
    val ordered = ArrayList<Int>(n)

    fun dfsOut(v: Int) {
        marked[v] = true
        for (u in outE[v]) {
            if (!marked[u]) {
                dfsOut(u)
            }
        }
        ordered.add(v)
    }

    for (v in 1..n) {
        if (!marked[v]) {
            dfsOut(v)
        }
    }
    ordered.reverse()

    val components = ArrayList<HashSet<Int>>()

    fun dfsIn(v: Int) {
        marked[v] = true
        components.last().add(v)
        for (u in inE[v]) {
            if (!marked[u]) {
                dfsIn(u)
            }
        }
    }

    marked.fill(false)
    for (v in ordered) {
        if (!marked[v]) {
            components.add(HashSet())
            dfsIn(v)
        }
    }

    val ans = ArrayList<Int>()
    out@ for (c in components) {
        for (v in c) {
            for (u in outE[v]) {
                if (!c.contains(u)) {
                    continue@out
                }
            }
        }
        ans.add(c.first())
    }

    println(ans.size)
    println(ans.joinToString(" "))
}