package racingcar.domain

class Car(name: String) {
    init {
        validateName(name)
    }

    private fun validateName(name: String) {
        if (name.isBlank()) {
            throw IllegalArgumentException("Car's name don't allowed to be blank")
        }

        if (name.length > 5) {
            throw IllegalArgumentException("Car's name should be below 5 characters")
        }
    }
}
