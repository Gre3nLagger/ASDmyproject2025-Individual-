package jetbrains.kotlin.course.alias.util

class IdentifierFactory {
    private var counter: Int = 0

    fun uniqueIdentifier(): Identifier {
        counter++
        return counter
    }
}
