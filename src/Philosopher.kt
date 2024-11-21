data class Philosopher(
    val name: String,
    val index: Int
) {
    fun leftStickIndex(philosopherCount: Int): Int = (index + 1) % philosopherCount
    fun rightStickIndex(): Int = index
}
