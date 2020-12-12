package intcode

data class ExecutionResult(
    val status: ExecutionStatus,
    val accumulator: Int
)