package racingcar.domain

import kotlin.random.Random

class Game(
    carNames: List<String>,
    private val conditionNumberGenerator: ConditionNumberGenerator = RandomConditionNumberGenerator()
) {
    private val cars: List<Car>

    init {
        validateCarNames(carNames)
        cars = carNames.stream()
            .map { Car(it) }
            .toList()
    }

    private fun validateCarNames(carNames: List<String>) {
        require(carNames.isNotEmpty()) { "car names should not be blank" }
    }

    fun play() {
        for (car in cars) {
            car.move(conditionNumberGenerator.generate())
        }
    }

    fun result(): List<Car> = cars

    fun winners(): List<Car> {
        return cars.filter { it.isSamePosition(winningPosition()) }
    }

    private fun winningPosition() : Position {
        var maxPosition = Position(0)
        for (car in cars) {
            if (car.position > maxPosition) {
                maxPosition = Position(car.position)
            }
        }
        return maxPosition
    }

    class RandomConditionNumberGenerator : ConditionNumberGenerator {

        private val random = Random

        override fun generate(): Int = random.nextInt(0, 10)
    }
}

