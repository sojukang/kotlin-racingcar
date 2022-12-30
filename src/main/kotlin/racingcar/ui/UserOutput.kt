package racingcar.ui

object UserOutput {

    fun carNamesRequestMessage() {
        println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).")
    }

    fun gameCountRequestMessage() {
        println("시도할 회수는 몇회인가요?")
    }

    fun resultInRacing(result: List<List<CarDTO>>) {
        println("${System.lineSeparator()}실행 결과")
        for (cars in result) {
            for (car in cars) {
                println("${car.name} : ${"-".repeat(car.position)}")
            }
            println()
        }
    }

    fun winners(winners: List<CarDTO>) {
        val winnerNames = winners.map { it.name }
        println("${winnerNames.joinToString(", ")}가 최종 우승했습니다.")
    }
}
