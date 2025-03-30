package jetbrains.kotlin.course.alias.util

import java.io.File
import kotlinx.serialization.*
import kotlinx.serialization.json.Json

//IdentifierFactory class is a class for generating unique identifiers
class IdentifierFactory {
    //Int property to store the last unique number. By default, counter should be zero.
    private var counter: Identifier = 0

    //uniqueIdentifier function returns a new unique identifier by incrementing the counter and returning it
    fun uniqueIdentifier(): Identifier {
        return counter++
    }
    fun saveLastIdentifier(counter: Int, filePath: String) {
        File(filePath).writeText(counter.toString())
    }
    fun loadLastIdentifier(filePath: String): Int {
        return File(filePath).readText().toInt()
    }



}
