package jetbrains.kotlin.course.alias.util

import java.io.File
import kotlinx.serialization.*
import kotlinx.serialization.json.Json

class IdentifierFactory {
    internal var counter: Int = 0

    fun uniqueIdentifier(): Identifier {
        counter++
        return counter
    }
    fun saveLastIdentifier(counter: Int, filePath: String) {
        File(filePath).writeText(counter.toString())
    }
    fun loadLastIdentifier(filePath: String): Int {
        return File(filePath).readText().toInt()
    }


}
