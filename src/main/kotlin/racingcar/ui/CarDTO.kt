package racingcar.ui

import racingcar.domain.Car

data class CarDTO(val name: String, val position: Int) {

    constructor(car: Car) : this(
        car.name, car.position.value
    )
}
