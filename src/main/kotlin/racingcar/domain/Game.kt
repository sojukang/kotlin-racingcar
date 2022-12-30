package racingcar.domain

import kotlin.random.Random

class Game(
    carNames: List<String>,
    private val conditionNumberGenerator: ConditionNumberGenerator = RandomConditionNumberGenerator()
) {
    val cars: List<Car>
        get() {
            return field.toList()
        }

    init {
        validateCarNames(carNames)
        cars = carNames.stream()
            .map { Car(it) }
            .toList()
    }

    private fun validateCarNames(carNames: List<String>) {
        require(carNames.isNotEmpty()) { "car names should not be blank" }
    }

    fun playWithTimes(count: Int): List<List<Car>> {
        validatePlayCount(count)
        val result = ArrayList<List<Car>>()
        for (i in 1..count) {
            play()
            result.add(cars)
        }
        return result
    }

    private fun validatePlayCount(count: Int) {
        require(count > 0) { "game count should be more than one" }
    }

    private fun play() {
        for (car in cars) {
            car.move(conditionNumberGenerator.generate())
        }
    }

    fun winners(): List<Car> {
        return cars.filter { it.isSamePosition(winningPosition()) }
    }

    private fun winningPosition(): Position {
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

