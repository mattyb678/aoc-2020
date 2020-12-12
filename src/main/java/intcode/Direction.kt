package intcode

enum class Direction(val abbreviation: String) {
    POSITIVE("+"),
    NEGATIVE("-")
    ;

    companion object {
        fun fromString(str: String): Direction? {
            return values().find { it.abbreviation == str }
        }
    }
}