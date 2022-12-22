package stringcalculator

import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class StringCalculatorTest : StringSpec({
    val stringCalculator = StringCalculator()

    "null 또는 빈문자인 경우 0을 반환한다." {
        stringCalculator.splitAndSum(null) shouldBe 0
        stringCalculator.splitAndSum("") shouldBe 0
    }

    "숫자 하나인 경우 그 값을 반환한다." {
        stringCalculator.splitAndSum("1") shouldBe 1
    }

    "쉼표(,)로 구분하여 더한다." {
        stringCalculator.splitAndSum("1,2") shouldBe 3
    }

    "콜론(:)으로 구분하여 더한다." {
        stringCalculator.splitAndSum("1:2") shouldBe 3
    }

    "쉼표(,) 또는 콜론(:)으로 구분하여 더한다." {
        stringCalculator.splitAndSum("1,2:3") shouldBe 6
    }

    "커스텀 구분자로 구분하여 더한다." {
        stringCalculator.splitAndSum("//;\\n1;2;3") shouldBe 6
    }

    "음수가 포함된 경우 예외를 던진다." {
        shouldThrowWithMessage<IllegalArgumentException>("negative number not allowed") {
            stringCalculator.splitAndSum("-1,2")
        }
    }
})
