package jetbrains.kotlin.course.alias.results

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
}
