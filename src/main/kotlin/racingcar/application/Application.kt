package racingcar.application

import racingcar.domain.Car
import racingcar.domain.Game
import racingcar.ui.CarDTO
import racingcar.ui.UserInput
import racingcar.ui.UserOutput

class Application

fun main(args: Array<String>) {
    val game = initializeGame()
    val result = playWithTimes(game)
    UserOutput.resultInRacing(result)
    UserOutput.winners(carsToDTOs(game.winners()))
}

fun initializeGame(): Game = try {
    UserOutput.carNamesRequestMessage()
    Game(UserInput.inputCarNames())
} catch (e: IllegalArgumentException) {
    println(e.message)
    initializeGame()
}

fun playWithTimes(game: Game): List<List<CarDTO>> = try {
    UserOutput.gameCountRequestMessage()
    val count = UserInput.inputGameCount()
    game.playWithTimes(count).map { carsToDTOs(it) }
} catch (e: IllegalArgumentException) {
    println(e.message)
    playWithTimes(game)
}

fun carsToDTOs(cars: List<Car>): List<CarDTO> = cars.map { CarDTO(it) }
