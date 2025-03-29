package jetbrains.kotlin.course.alias.storage

import jetbrains.kotlin.course.alias.card.CardService
import jetbrains.kotlin.course.alias.results.GameResultsService
import jetbrains.kotlin.course.alias.team.TeamService
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import org.springframework.stereotype.Service
import java.io.File

@Service
class GameSaveService(
    private val teamService: TeamService,
    private val cardService: CardService,
    private val gameResultsService: GameResultsService
) {

    // Save the current game state
    fun saveGame(fileName: String) {
        val saveData = mapOf(
            "gameHistory" to gameResultsService.getAllGameResults(),
            "teamsStorage" to teamService.getAllTeams(),
            "lastTeamId" to teamService.identifierFactory.counter,
            "lastCardId" to cardService.identifierFactory.counter,
            "cards" to cardService.getAllCards()
        )
        val json = Json.encodeToString(saveData)
        File("savedGames/$fileName.json").writeText(json)
    }

    // Load a saved game
    fun loadGame(fileName: String): Map<String, Any> {
        val json = File("savedGames/$fileName.json").readText()
        return Json.decodeFromString(json)
    }
}
