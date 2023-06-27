import kotlin.math.min

fun main() {
    val s = readln()
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

    var i = 0
    while ((i + z[i] < n || n % i != 0)) {
        i++
        if (i == n) {
            break
        }
    }
    println(i)
}