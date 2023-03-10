import kotlin.math.min

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val adjList = Array(n + m + 1) { ArrayList<Int>() }
    repeat(m) { i ->
        val v = i + n + 1
        val (v1, v2, v3) = readln().split(" ").map { it.toInt() }
        adjList[v].add(v1)
        adjList[v1].add(v)
        adjList[v].add(v2)
        adjList[v2].add(v)
        adjList[v].add(v3)
        adjList[v3].add(v)
    }
    val marked = BooleanArray(n + m + 1)
    val a = BooleanArray(n + m + 1)
    val d = IntArray(n + m + 1)
    val up = IntArray(n + m + 1)
    var t = 1

    fun dfs(v: Int, p: Int) {
        marked[v] = true
        t++
        d[v] = t
        up[v] = t
        var c = 0
        for (u in adjList[v]) {
            if (u == p) continue
            if (marked[u])
                up[v] = min(up[v], d[u])
            else {
                dfs(u, v)
                c++
                up[v] = min(up[v], up[u])
                if (up[u] >= d[v] && p != -1) a[v] = true
            }
        }
        if (p == -1 && c > 1) a[v] = true
    }

    for (v in 1..n + m) {
        if(!marked[v]) {
            dfs(v, -1)
        }
    }

    val ans = ArrayList<Int>()
    for (v in n + 1..n + m) {
        if (a[v]) {
            ans.add(v - n)
        }
    }

    println(ans.size)
    if (ans.size > 0) {
        println(ans.joinToString(" "))
    }
}