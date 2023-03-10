fun main() {
    val n = readln().toInt()
    val k = readln().toInt()
    val exits = readln().split(" ").map { it.toInt() }.sorted()
    val m = readln().toInt()
    val adjList = Array(n + 1) { ArrayList<Int>() }
    repeat(m) {
        val (u, v) = readln().split(" ").map { it.toInt() }
        adjList[v].add(u)
        adjList[u].add(v)
    }
    val d = IntArray(n + 1)
    d.fill(Int.MAX_VALUE)
    val ex = IntArray(n + 1)
    ex.fill(Int.MAX_VALUE)
    val q = ArrayDeque<Int>()
    exits.forEach {
        d[it] = 0
        ex[it] = it
        q.add(it)
    }

    while (q.isNotEmpty()) {
        val u = q.removeFirst()
        for (v in adjList[u]) {
            if (d[v] == Int.MAX_VALUE || (ex[v] > ex[u] && d[v] > d[u])) {
                d[v] = d[u] + 1
                ex[v] = ex[u]
                q.add(v)
            }
        }
    }

    println(d.drop(1).joinToString(" "))
    println(ex.drop(1).joinToString(" "))
}