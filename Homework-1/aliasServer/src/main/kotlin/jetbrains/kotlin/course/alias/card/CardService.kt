package jetbrains.kotlin.course.alias.card

import jetbrains.kotlin.course.alias.util.Identifier
import jetbrains.kotlin.course.alias.util.IdentifierFactory
import jetbrains.kotlin.course.alias.util.StorageConfig
import jetbrains.kotlin.course.alias.util.words
import org.springframework.stereotype.Service
import java.io.File
import kotlinx.serialization.*
import kotlinx.serialization.json.Json

@Service

class CardService(
    //property identifierFactory with the type IdentifierFactory to generate identifiers for each card.
    //default value for it by creating a new instance of the IdentifierFactory class.
    private val identifierFactory: IdentifierFactory = IdentifierFactory()
) {
    //property cards that stores a list of cards (List<Card>).
    // Initialized by calling the generateCards method.
    private val cards: List<Card> = generateCards()

    companion object {
        private val usedCards: MutableList<Card> = mutableListOf()
        // WORDS_IN_CARD to store the number of words for the cards and assigned the value 4.
        private const val WORDS_IN_CARD = 4

        // Convert the predefined list of words into an immutable list.
        private val wordsList = words.toList()

        // Calculate the total number of cards as words.size divided by WORDS_IN_CARD.
        val cardsAmount: Int = wordsList.size / WORDS_IN_CARD
    }

    //Add the toWords function to the CardService class,
    // which is an extension function for List<String> and converts each element from this list into Word.
    private fun List<String>.toWords(): List<Word> = this.map { Word(it) }

    //generateCards function, which shuffles the words list, splits it into chunks with WORDS_IN_CARD words,
    // takes cardsAmount chunks for cardsAmount cards, and finally creates a new Card for each chunk.
    private fun generateCards(): List<Card> {
        return wordsList.shuffled()
            .chunked(WORDS_IN_CARD)
            .take(cardsAmount)
            .map {
                val id = identifierFactory.uniqueIdentifier()
                println("Generated card with ID: $id") // Debugging log
                Card(id, it.toWords())
            }
    }


    //Implement the getCardByIndex method, which accepts index (an integer number) and the Card at this index.
    fun getCardByIndex(index: Int): Card {
        // If the card does not exist, throw an error.
        return cards.getOrNull(index) ?: throw IllegalArgumentException("Invalid card index: $index")
    }
    fun saveCards(cards: List<Card>, filePath: String) {
        val json = Json.encodeToString(cards)
        File(filePath).writeText(json)
    }
    fun loadCards(filePath: String): List<Card> {
        val file = File(filePath)
        if (!file.exists() || file.readText().isEmpty()) {
            println("No cards found or file is empty, returning empty list.")
            return emptyList()
        }
        println("Loading cards from $filePath")  // Debugging log
        return Json.decodeFromString(file.readText())
    }
    fun getAllCards(): List<Card> {
        return cards
    }
    fun saveUsedCards() {
        val json = Json.encodeToString(usedCards)
        File(StorageConfig.USED_CARDS_FILE).writeText(json)
    }

    fun loadUsedCards() {
        val file = File(StorageConfig.USED_CARDS_FILE)
        if (file.exists()) {
            usedCards.clear()
            usedCards.addAll(Json.decodeFromString(file.readText()))
        }
    }


}
