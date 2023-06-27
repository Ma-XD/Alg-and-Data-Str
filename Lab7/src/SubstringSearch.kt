import kotlin.math.min

fun main() {
    val p = readln()
    val t = readln()
    val s = "$p$$t"
    val n = s.length
    val z = IntArray(n)
    var l = 0
    var r = 0
    for ( i in 1 until n) {
        if (i <= r) {
            z[i] = min(z[i - l], r - i + 1)
        }
        while (i + z[i] < n && s[z[i]] == s[i + z[i]]) {
            z[i]++
        }
        if (i + z[i] - 1 > r) {
            l = i
            r = i + z[i] - 1
        }
    }

    val ans = ArrayList<Int>()
    for (i in p.length + 1 .. t.length + 1) {
        if (z[i] == p.length) {
            ans.add(i - p.length)
        }
    }
    println(ans.size)
    if (ans.size > 0) {
        println(ans.joinToString(" "))
    }
}