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

        expect("중복 사용자가 존재할 경우 예외를 던진다.") {
            shouldThrowWithMessage<IllegalArgumentException>("car names should be unique") {
                Game(listOf("Forky", "Forky"))
            }
        }
    }

    context("게임 2회 진행") {
        val game = Game(listOf("Forky", "Quan", "YJ"), TestConditionNumberGenerator(listOf(4, 4, 3, 4, 4, 4)))

        expect("진행 횟수가 양수가 아닌 경우 예외를 던진다.") {
            shouldThrowWithMessage<IllegalArgumentException>("game count should be more than one") {
                game.playWithTimes(0)
            }
        }

        game.playWithTimes(2)

        expect("첫 번째, 두 번째 자동차는 2 번, 세 번째 자동차는 한 번 전진한다.") {
            val result = game.cars

            result[0].position shouldBe Position(2)
            result[1].position shouldBe Position(2)
            result[2].position shouldBe Position(1)
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
