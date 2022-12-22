package racingcar.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PositionTest : StringSpec({

    "값이 음수인 경우 예외를 반환한다." {
        shouldThrowWithMessage<IllegalArgumentException>("Position value should be positive") {
            Position(-1)
        }
    }

    "값이 조건을 충족하는 경우 생성한다." {
        shouldNotThrowAny { Position(0) }
    }

    "값을 증가시킨다." {
        val position = Position()
        position.up()
        position shouldBe Position(1)
    }
})
