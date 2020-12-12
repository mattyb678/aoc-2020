package intcode

class Instruction(opStr: String, dirStr: String, val count: Int) {

    var operation: Operation = Operation.fromString(opStr)!!
        private set

    val direction: Direction = Direction.fromString(dirStr)!!

    val executed: Boolean
        get() = this.executionCount > 0

    private var executionCount: Int = 0

    fun execute() {
        executionCount++
    }

    fun reset() {
        executionCount = 0
    }

    fun changeOperation(newOperation: Operation) {
        this.operation = newOperation
    }

    override fun toString(): String {
        return "Instruction(count=$count, operation=$operation, direction=$direction, executionCount=$executionCount)"
    }

}