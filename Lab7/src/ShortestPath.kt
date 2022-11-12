import kotlin.math.min

fun main() {
    val (n, m, s, t) = readln().split(" ").map { it.toInt() }
    val outE = Array(n + 1) { ArrayList<Pair<Int, Int>>() }
    repeat(m) {
        val (v, u, w) = readln().split(" ").map { it.toInt() }
        outE[v].add(u to w)
    }

    val marked = BooleanArray(n + 1)
    val d = Array(n + 1) { Int.MAX_VALUE }
    d[t] = 0

    fun dfs(v: Int) {
        marked[v] = true
        for (e in outE[v]) {
            if (!marked[e.first]) {
                dfs(e.first)
            }
            if (d[e.first] < Int.MAX_VALUE) {
                d[v] = min(d[v], d[e.first] + e.second)
            }
        }
    }

    dfs(s)
    println(if (d[s] == Int.MAX_VALUE) "Unreachable" else d[s])
}
