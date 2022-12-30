package stringcalculator

private val CUSTOM_DELIMITER_PATTERN = Regex("//(.)\\\\n(.*)")

class StringCalculator {

    fun splitAndSum(input: String?): Number = sum(split(input))

    private fun sum(input: List<String>): Number {
        val numbers = input.map { it.toLong() }.toList()
        validateNumbers(numbers)
        return numbers.sum()
    }

    private fun validateNumbers(numbers: List<Long>) {
        if (numbers.any { it < 0 }) {
            throw IllegalArgumentException("negative number not allowed")
        }
    }

    private fun split(input: String?): List<String> =
        when {
            input == null || input.isBlank() -> listOf("0")
            isCustomDelimiter(input) -> splitByCustomDelimiter(input)
            else -> input.split(",", ":")
        }

    private fun isCustomDelimiter(input: String): Boolean {
        return CUSTOM_DELIMITER_PATTERN.matches(input)
    }

    private fun splitByCustomDelimiter(input: String): List<String> {
        val groupValues = CUSTOM_DELIMITER_PATTERN.find(input)!!.groups
        val customDelimiter = groupValues[1]!!.value
        return groupValues[2]!!.value.split(customDelimiter)
    }
}
