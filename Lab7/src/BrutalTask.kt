    fun main() {
    val n = readln().toInt()
    val d = IntArray(n + 1)
    d[2] = 1
    val has = BooleanArray(n + 1)
    for (i in 3..n) {
        for (j in 1..i) {
            has[d[j - 1] xor d[i - j]] = true
        }
        var g = 0
        while (has[g]) g++
        d[i] = g
        has.fill(false)
    }
    if (d[n] > 0) {
        println("Schtirlitz")
        for (j in 1..n) {
            if (d[j - 1] xor d[n - j] == 0) {
                print("$j ")
            }
        }
    } else {
        println("Mueller")
    }
}