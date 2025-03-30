package jetbrains.kotlin.course.alias.results

import java.io.File
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import jetbrains.kotlin.course.alias.team.Team
import jetbrains.kotlin.course.alias.team.TeamService
import org.springframework.stereotype.Service

// type alias GameResult referring to List<Team>
typealias GameResult = List<Team>

@Service
class GameResultsService(private val teamService: TeamService = TeamService()) {

    //companion object declaring the gameHistory variable
    companion object {
        //storing the list of game results (MutableList<GameResult>).initialized via an empty list.
        private val gameHistory: MutableList<GameResult> = mutableListOf()
    }

    //saveGameResults method that adds the result to the gameHistory
    fun saveGameResults(result: GameResult) {
        if (result.isEmpty()) {
            //Throws an error if the result is empty.
            throw IllegalArgumentException("Game result cannot be empty.")
        }

        //all team IDs from the result must be in the TeamService.teamsStorage
        val invalidTeams = result.filterNot { teamService.isTeamExists(it.id) }
        if (invalidTeams.isNotEmpty()) {
            //Throws an error if any team in the result is not found in TeamService.teamsStorage
            throw IllegalArgumentException("Some teams in the result do not exist in TeamService storage.")
        }


        gameHistory.add(result)
    }

    //getAllGameResults method that returns the reversed gameHistory list
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
    fun restoreGameResults(results: List<GameResult>) {
        gameHistory.clear()
        gameHistory.addAll(results)
    }


}
