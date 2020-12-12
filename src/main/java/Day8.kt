import intcode.*

class Day8 : Day {

    companion object {
        private val instructionRegex = "([a-z]{3}) ([+|-])(\\d*)".toRegex()
    }

    override fun asInt(): Int = 8

    override fun part1InputName(): String = "day8"

    override fun part1(input: List<String>): String {
        return input.toInstructions().execute().accumulator.toString()
    }

    private fun accumulate(accumulator: Int, direction: Direction, count: Int): Int {
        var result = accumulator
        when (direction) {
            Direction.POSITIVE -> result += count
            Direction.NEGATIVE -> result -= count
        }
        return result
    }

    override fun part2InputName(): String = "day8"

    override fun part2(input: List<String>): String {
        val instructions = input.toInstructions()
        for (instruction in instructions) {
            val originalOperation = if (instruction.operation == Operation.NO_OPERATION) {
                instruction.changeOperation(Operation.JUMP)
                Operation.NO_OPERATION
            } else if (instruction.operation == Operation.JUMP) {
                instruction.changeOperation(Operation.NO_OPERATION)
                Operation.JUMP
            } else {
                continue
            }
            instructions.forEach { it.reset() }
            val result = instructions.execute()
            if (result.status == ExecutionStatus.SUCCESS) {
                return result.accumulator.toString()
            }
            instruction.changeOperation(originalOperation)
        }
        return ""
    }

    private fun List<String>.toInstructions(): List<Instruction> {
        return this.map { instructionRegex.find(it) }
            .mapNotNull { it?.destructured }
            .map { (op, dir, num) -> Instruction(op, dir, num.toInt()) }
    }

    private fun List<Instruction>.execute(): ExecutionResult {
        var pointerCounter = 0
        var pointer = this[pointerCounter]
        var accumulator = 0

        while (true) {
            if (pointer.executed) {
                return ExecutionResult(ExecutionStatus.INFINITE_LOOP, accumulator)
            }
            when (pointer.operation) {
                Operation.NO_OPERATION -> {
                    pointerCounter++
                }
                Operation.ACCUMULATE -> {
                    accumulator = accumulate(accumulator, pointer.direction, pointer.count)
                    pointerCounter++
                }
                Operation.JUMP -> {
                    pointerCounter = accumulate(pointerCounter, pointer.direction, pointer.count)
                }
            }
            if (pointerCounter >= this.size) {
                return ExecutionResult(ExecutionStatus.SUCCESS, accumulator)
            }
            pointer.execute()
            pointer = this[pointerCounter]
        }
    }
}