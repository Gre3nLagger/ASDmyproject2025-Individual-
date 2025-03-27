package jetbrains.kotlin.course.alias.storage

import jetbrains.kotlin.course.alias.results.GameResultsService
import jetbrains.kotlin.course.alias.team.TeamService
import jetbrains.kotlin.course.alias.card.CardService
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import java.io.File

@Serializable
data class GameState(
    val gameHistory: List<GameResult>,
    val teamsStorage: Map<String, Team>,
    val teamCounter: Int,
    val cardCounter: Int,
    val usedWords: List<String>
)

class FileStorageService {

    private val gameStateFile = File("gameState.json")

    fun saveGameState() {
        val gameState = GameState(
            gameHistory = GameResultsService.gameHistory,
            teamsStorage = TeamService.teamsStorage,
            teamCounter = TeamService.identifierFactory.counter,
            cardCounter = CardService.identifierFactory.counter,
            usedWords = CardService.usedWords.map { it.word } // Adjust according to your model
        )
        gameStateFile.writeText(Json.encodeToString(gameState))
    }

    fun loadGameState() {
        if (gameStateFile.exists()) {
            val gameState = Json.decodeFromString<GameState>(gameStateFile.readText())
            GameResultsService.gameHistory.clear()
            GameResultsService.gameHistory.addAll(gameState.gameHistory)
            TeamService.teamsStorage.clear()
            TeamService.teamsStorage.putAll(gameState.teamsStorage)
            TeamService.identifierFactory.counter = gameState.teamCounter
            CardService.identifierFactory.counter = gameState.cardCounter
            CardService.usedWords.clear()
            CardService.usedWords.addAll(gameState.usedWords.map { Word(it) }) // Adjust according to your model
        }
    }
}
