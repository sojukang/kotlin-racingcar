package racingcar.application

import racingcar.domain.Car
import racingcar.domain.Game
import racingcar.ui.CarDTO
import racingcar.ui.UserInput
import racingcar.ui.UserOutput

class Application

fun main(args: Array<String>) {
    // Interactively need user input
    val game = initialize()
    val count = requestGameCount()

    // Play and print a result of game
    val result = play(game, count)
    UserOutput.resultInRacing(result)
    UserOutput.winners(carsToDTOs(game.winners()))
}

fun initialize(): Game = try {
    UserOutput.requestCarNamesMessage()
    Game(UserInput.inputCarNames())
} catch (e: RuntimeException) {
    println(e.message)
    initialize()
}

fun requestGameCount(): Int = try {
    UserOutput.requestGameCountMessage()
    UserInput.inputGameCount()
} catch (e: RuntimeException) {
    println(e.message)
    requestGameCount()
}

fun play(game: Game, count: Int): List<List<CarDTO>> {
    val result = ArrayList<List<CarDTO>>()

    for (i in 1..count) {
        game.play()
        result.add(carsToDTOs(game.cars))
    }
    return result
}

fun carsToDTOs(cars: List<Car>): List<CarDTO> {
    return cars.map { CarDTO(it) }
}
