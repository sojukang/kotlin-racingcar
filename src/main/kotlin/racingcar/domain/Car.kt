package racingcar.domain

class Car(name: String) {

    private val movingStrategy: MovingStrategy
    val position: Position

    init {
        validateName(name)
        this.movingStrategy = DefaultMovingStrategy()
        this.position = Position()
    }

    private fun validateName(name: String) {
        require(name.isNotBlank()) { "Car's name don't allowed to be blank" }
        require(name.length <= 5) { "Car's name should be below 5 characters" }
    }

    fun move(number: Int) {
        movingStrategy.move(position, number)
    }

    class DefaultMovingStrategy : MovingStrategy {

        override fun move(position: Position, number: Int) {
            if (isMovable(number)) {
                position.up()
            }
        }

        private fun isMovable(number: Int): Boolean {
            return number >= 4
        }
    }
}
