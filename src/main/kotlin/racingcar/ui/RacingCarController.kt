package racingcar.ui

import racingcar.domain.Car
import racingcar.domain.Game

object RacingCarController {

    fun start() {
        val game = initializeGame()
        val result = playGame(game)
        UserOutput.resultInRacing(result)
        UserOutput.winners(carsToDTOs(game.winners()))
    }

    private fun initializeGame(): Game = tryWithFail {
        UserOutput.carNamesRequestMessage()
        Game(UserInput.inputCarNames())
    }

    private fun playGame(game: Game): List<List<CarDTO>> = tryWithFail {
        UserOutput.gameCountRequestMessage()
        val count = UserInput.inputGameCount()
        game.playWithTimes(count)
    }
}

private fun carsToDTOs(cars: List<Car>): List<CarDTO> = cars.map { CarDTO(it) }

private fun <T> tryWithFail(body: () -> T): T = try {
    body.invoke()
} catch (e: IllegalArgumentException) {
    println(e.message)
    tryWithFail(body)
}

private fun Game.playWithTimes(count: Int): List<List<CarDTO>> {
    require(count > 0) { "game count should be more than one" }
    val result = ArrayList<List<CarDTO>>()
    for (i in 1..count) {
        play()
        result.add(carsToDTOs(cars))
    }
    return result
}
