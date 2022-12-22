package racingcar.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.core.spec.style.StringSpec

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
}) {
}
