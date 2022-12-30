package racingcar.ui

import java.util.*

object UserInput {

    private val reader: Scanner = Scanner(System.`in`)

    fun inputCarNames(): List<String> {
        val carNames = reader.nextLine().removeWhiteSpaces()
        return carNames.split(",")
    }

    fun inputGameCount(): Int {
        return reader.nextInt()
    }

    private fun String.removeWhiteSpaces() = replace(" ", "")
}
