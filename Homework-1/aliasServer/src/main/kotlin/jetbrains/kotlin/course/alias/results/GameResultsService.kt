package jetbrains.kotlin.course.alias.results

import java.io.File
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import jetbrains.kotlin.course.alias.team.Team
import jetbrains.kotlin.course.alias.team.TeamService
import org.springframework.stereotype.Service

// Type alias for game results
typealias GameResult = List<Team>

@Service
class GameResultsService(private val teamService: TeamService) {

    companion object {
        private val gameHistory: MutableList<GameResult> = mutableListOf()
    }

    fun saveGameResults(result: GameResult) {
        if (result.isEmpty()) {
            throw IllegalArgumentException("Game result cannot be empty.")
        }


        val invalidTeams = result.filterNot { teamService.isTeamExists(it.id) }
        if (invalidTeams.isNotEmpty()) {
            throw IllegalArgumentException("Some teams in the result do not exist in TeamService storage.")
        }


        gameHistory.add(result)
    }

    fun getAllGameResults(): List<GameResult> {
        return gameHistory.reversed()
    }

    fun saveGameHistory(gameHistory: List<GameResult>, filePath: String) {
        val json = Json.encodeToString(gameHistory)
        File(filePath).writeText(json)
    }
    fun loadGameHistory(filePath: String): List<GameResult> {
        val json = File(filePath).readText()
        return Json.decodeFromString(json)
    }

}
