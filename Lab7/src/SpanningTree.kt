import kotlin.math.hypot
import kotlin.math.min


fun d(p1: Pair<Double, Double>, p2: Pair<Double, Double>) = hypot(p1.first - p2.first, p1.second - p2.second)

fun main() {
    val n = readln().toInt()
    val points = ArrayList<Pair<Double, Double>>(n)
    repeat(n) {
        val (x, y) = readln().split(" ").map { it.toDouble() }
        points.add(x to y)
    }
    val marked = BooleanArray(n)
    val d = Array(n) { Double.MAX_VALUE }
    d[0] = 0.0
    var ans = 0.0
    repeat(n) {
        var vi = 0
        var minDist = Double.MAX_VALUE
        repeat(n) { vj ->
            if (!marked[vj] && d[vj] < minDist) {
                vi = vj
                minDist = d[vj]
            }
        }
        ans += minDist
        marked[vi] = true
        repeat(n) { ui ->
            d[ui] = min(d(points[vi], points[ui]), d[ui])
        }
    }

    println(ans)
}