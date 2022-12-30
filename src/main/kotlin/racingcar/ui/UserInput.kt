package racingcar.ui

import java.util.*

object UserInput {

    private val reader: Scanner = Scanner(System.`in`)

    fun inputCarNames(): List<String> {
        val carNames = reader.nextLine().removeWhiteSpaces()
        return carNames.split(",")
    }

    fun inputGameCount(): Int = try {
        reader.nextInt()
    } catch (e: InputMismatchException) {
        println("please try again. game count should be natural number.")
        reader.nextLine()
        inputGameCount()
    }

    private fun String.removeWhiteSpaces() = replace(" ", "")
}
