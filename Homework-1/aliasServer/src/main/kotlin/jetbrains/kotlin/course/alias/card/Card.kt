package jetbrains.kotlin.course.alias.card

import jetbrains.kotlin.course.alias.util.Identifier
import kotlinx.serialization.Serializable

//data class Card to store information for each card.
// id with the Identifier type and a list of words (List<Word>)
@Serializable
data class Card(
    val id: Identifier,
    val words: List<Word>
)
