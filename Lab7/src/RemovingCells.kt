fun main() {
    val (m, n) = readln().split(" ").map { it.toInt() }
    val cells = Array(m) { readln() }
    val neighbors = Array(n * m) { ArrayList<Int>() }

    fun idx(r: Int, c: Int) = r * n + c

    repeat(m) { r ->
        repeat(n) {c ->
            if (cells[r][c] == 'O') {
                if (r > 0 && cells[r - 1][c] == 'O') neighbors[idx(r, c)].add(idx(r - 1, c))
                if (c > 0 && cells[r][c - 1] == 'O') neighbors[idx(r, c)].add(idx(r, c - 1))
                if (r < m - 1 && cells[r + 1][c] == 'O') neighbors[idx(r, c)].add(idx(r + 1, c))
                if (c < n - 1 && cells[r][c + 1] == 'O') neighbors[idx(r, c)].add(idx(r, c + 1))
            }
        }
    }

    val marked = BooleanArray(m * n)

    fun dfs(v: Int) {
        marked[v] = true
        for (u in neighbors[v]) {
            if (!marked[u]) {
                dfs((u))
            }
        }
    }
    var cnt = 0
    repeat(m) { r ->
        repeat(n) { c ->
            if (cells[r][c] == 'O' && !marked[idx(r, c)]) {
                dfs(idx(r, c))
                cnt++
            }
        }
    }

    println(cnt)
}