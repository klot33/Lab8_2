fun main() {
    println("Укажите числом количество философов за круглым столом, указав целое положительное число:")
    var philosopherCount: Int?

    while (true) {
        print("Введите количество философов: ")
        philosopherCount = readLine()?.toIntOrNull()

        if (philosopherCount == null || philosopherCount < 1) {
            println("Ошибка: Количество философов должно быть больше нуля. Попробуйте снова.")
            continue
        }
        break
    }

    val philosophers = mutableListOf<Philosopher>()
    for (i in 1..philosopherCount) {
        print("Имя философа номер $i - ")
        val name = readLine()?.ifBlank { "$i".padStart(2, '0') } ?: "$i".padStart(2, '0')
        philosophers.add(Philosopher(name, i - 1))
    }

    val sticks = MutableList(philosopherCount) { Sticks(it) }

    val randomOrder = philosophers.toMutableList()

    while (randomOrder.isNotEmpty()) {
        randomOrder.shuffle()

        val philosopher = randomOrder.removeFirst()
        val leftStick = sticks[philosopher.leftStickIndex(philosopherCount)]
        val rightStick = sticks[philosopher.rightStickIndex()]

        if (!leftStick.isTaken && !rightStick.isTaken) {
            leftStick.isTaken = true
            rightStick.isTaken = true
            println("${philosopher.name} взял палочки слева и справа, и обедает")
        } else {
            println("${philosopher.name} размышляет")
        }
    }
}