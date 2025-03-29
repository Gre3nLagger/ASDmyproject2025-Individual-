package jetbrains.kotlin.course.alias.card

import jetbrains.kotlin.course.alias.util.Identifier
import jetbrains.kotlin.course.alias.util.IdentifierFactory
import org.springframework.stereotype.Service
import java.io.File
import kotlinx.serialization.*
import kotlinx.serialization.json.Json

@Service
class CardService(
    internal val identifierFactory: IdentifierFactory = IdentifierFactory()
) {

    private val cards: List<Card> = generateCards()

    companion object {
        private const val WORDS_IN_CARD = 4
        private val words = listOf(
            "Apple", "Banana", "Carrot", "Dog", "Elephant", "Fish", "Guitar", "House",
            "Ice", "Jacket", "Kite", "Lamp", "Mountain", "Notebook", "Orange", "Pencil"
        )
        val cardsAmount: Int = words.size / WORDS_IN_CARD
    }

    private fun List<String>.toWords(): List<Word> = this.map { Word(it) }

    private fun generateCards(): List<Card> {
        return words.shuffled()
            .chunked(WORDS_IN_CARD)
            .take(cardsAmount)
            .map { Card(identifierFactory.uniqueIdentifier(), it.toWords()) }
    }

    fun getCardByIndex(index: Int): Card {
        return cards.getOrNull(index) ?: throw IllegalArgumentException("Invalid card index: $index")
    }
    fun saveCards(cards: List<Card>, filePath: String) {
        val json = Json.encodeToString(cards)
        File(filePath).writeText(json)
    }
    fun loadCards(filePath: String): List<Card> {
        val json = File(filePath).readText()
        return Json.decodeFromString(json)
    }
    fun getAllCards(): List<Card> {
        return cards
    }


}
