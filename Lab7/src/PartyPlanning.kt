fun not(str: String): String {
    val key = str.substring(1, str.length)
    return if (str.first() == '+') "-${key}" else "+${key}"
}

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val outE = HashMap<String, HashSet<String>>()
    val inE = HashMap<String, HashSet<String>>()
    repeat(n) {
        val name = readln()
        outE["+$name"] = HashSet()
        inE["+$name"] = HashSet()
        outE["-$name"] = HashSet()
        inE["-$name"] = HashSet()
    }
    repeat(m) {
        val (u, v) = readln().split(" => ")
        outE[u]?.add(v)
        inE[v]?.add(u)
        outE[not(v)]?.add(not(u))
        inE[not(u)]?.add(not(v))
    }

    val marked = HashSet<String>()
    val ordered = ArrayList<String>(n)

    fun dfsOut(v: String) {
        marked.add(v)
        for (u in outE[v]!!) {
            if (!marked.contains(u)) {
                dfsOut(u)
            }
        }
        ordered.add(v)
    }

    for (v in outE.keys) {
        if (!marked.contains(v)) {
            dfsOut(v)
        }
    }

    ordered.reverse()
    marked.clear()
    val components = HashMap<String, Int>()
    var countComponents = 0

    fun dfsIn(v: String) {
        marked.add(v)
        components[v] = countComponents
        for (u in inE[v]!!) {
            if (!marked.contains(u)) {
                dfsIn(u)
            }
        }
    }

    for (v in ordered) {
        if (!marked.contains(v)) {
            countComponents++
            dfsIn(v)
        }
    }

    val list = ArrayList<String>()
    for (v in outE.keys) {
        if (components[v] == components[not(v)]) {
            println(-1)
            return
        }
        if (v.first() == '+' && components[v]!! > components[not(v)]!!) {
            list.add(v.substring(1, v.length))
        }
    }
    println(list.size)
    println(list.joinToString("\n"))
}