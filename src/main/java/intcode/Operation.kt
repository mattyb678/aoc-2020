package intcode

enum class Operation(val abbreviation: String) {
    ACCUMULATE("acc"),
    JUMP("jmp"),
    NO_OPERATION("nop")
    ;

    companion object {
        fun fromString(str: String): Operation? {
            return values().find { it.abbreviation == str }
        }
    }
}