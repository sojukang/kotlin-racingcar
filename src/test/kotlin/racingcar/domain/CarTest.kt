package racingcar.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CarTest : StringSpec({

    "이름이 비거나 5글자를 초과하면 예외를 반환한다." {
        shouldThrowWithMessage<IllegalArgumentException>("Car's name don't allowed to be blank") {
            Car("")
        }
        shouldThrowWithMessage<IllegalArgumentException>("Car's name should be below 5 characters") {
            Car("123456")
        }
    }

    "이름이 조건을 충족하는 경우 생성한다" {
        shouldNotThrowAny { Car("Forky") }
    }

    "숫자가 4 이상인 경우 전진한다." {
        val car = Car("Forky")
        car.move(4)
        car.position shouldBe Position(1)
    }

    "숫자가 4 미만인 경우 정지한다." {
        val car = Car("Forky")
        car.move(3)
        car.position shouldBe Position(0)
    }
})
