package racingcar.domain

class Position(initValue: Int = 0) {

    constructor(initPosition: Position) : this(initPosition.value)

    private var value: Int

    init {
        validate(initValue)
        value = initValue
    }

    private fun validate(initValue: Int) {
        require(initValue >= 0) { "Position value should be positive" }
    }

    fun up() {
        value++
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Position

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value
    }

    operator fun compareTo(other: Position): Int {
        return value.compareTo(other.value)
    }
}
