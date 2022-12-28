package racingcar.domain

import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.shouldBe

class GameTest : ExpectSpec({

    context("게임 생성") {
        expect("빈 사용자 목록일 경우 예외를 던진다.") {
            shouldThrowWithMessage<IllegalArgumentException>("car names should not be blank") {
                Game(listOf())
            }
        }
    }

    context("게임 1회 진행") {
        val game = Game(listOf("Forky", "Quan", "YJ"), TestConditionNumberGenerator(listOf(4, 4, 3)))
        game.play()

        expect("첫 번째, 두 번째 자동차는 전진, 세 번째 자동차는 정지한다.") {
            val result = game.result()

            result[0].position shouldBe Position(1)
            result[1].position shouldBe Position(1)
            result[2].position shouldBe Position(0)
        }

        expect("첫 번째, 두 번째 자동차가 우승자이다.") {
            val winners = game.winners()

            winners[0].name shouldBe "Forky"
            winners[1].name shouldBe "Quan"
        }
    }
}) {
    class TestConditionNumberGenerator(numbers: List<Int>) : ConditionNumberGenerator {

        private val numbers = ArrayDeque(numbers)

        override fun generate(): Int = numbers.removeFirst()
    }
}
