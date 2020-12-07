import org.clapper.util.classutil.*
import java.io.File
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    println("Advent of Code - 2020")

    val dayPart = args[0].split(".")
    val dayToRun = dayPart[0].toInt()
    val runPart1 = dayPart[1].contains("1")
    val runPart2 = dayPart[1].contains("2")

    val finder = ClassFinder()
    finder.addClassPath()

    val filter = AndClassFilter(
        NotClassFilter(InterfaceOnlyClassFilter()),
        SubclassClassFilter(Day::class.java),
        NotClassFilter(AbstractClassFilter())
    )

    val foundClasses = ArrayList<ClassInfo>()
    finder.findClasses(foundClasses, filter)

    val day = foundClasses.map { Class.forName(it.className) }
        .map { it.getDeclaredConstructor().newInstance() as Day }
        .firstOrNull { it.asInt() == dayToRun }

    if (day == null) {
        System.err.println("Could not find day: $dayToRun")
        exitProcess(-2)
    }

    if (runPart1) {
        val part1Resource = day.part1InputName()
        val part1Input = readFileAsLines("src/main/resources/$part1Resource")
        val part1Answer = day.part1(part1Input)
        println("Part 1 answer: $part1Answer")
    }

    if (runPart2) {
        val part2Resource = day.part2InputName()
        val part2Input = readFileAsLines("src/main/resources/$part2Resource")
        val part2Answer = day.part2(part2Input)
        println("Part 2 answer: $part2Answer")
    }
}

fun readFileAsLines(fileName: String): List<String>
        = File(fileName).useLines { it.toList() }