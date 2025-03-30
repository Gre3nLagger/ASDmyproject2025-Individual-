package jetbrains.kotlin.course.alias.card

import jetbrains.kotlin.course.alias.util.Identifier

//data class Card to store information for each card.
// id with the Identifier type and a list of words (List<Word>)
data class Card(
    val id: Identifier,
    val words: List<Word>
)
