package jetbrains.kotlin.course.alias.team

import jetbrains.kotlin.course.alias.util.Identifier
import jetbrains.kotlin.course.alias.util.IdentifierFactory
import org.springframework.stereotype.Service

@Service
class TeamService(
    private val identifierFactory: IdentifierFactory = IdentifierFactory()
) {

    companion object {
        val teamsStorage: MutableMap<Identifier, Team> = mutableMapOf()

        // ✅ Public getter for accessing teamsStorage
        val allTeams: Map<Identifier, Team> get() = teamsStorage
    }

    fun generateTeamsForOneRound(teamsNumber: Int): List<Team> {
        val newTeams = List(teamsNumber) {
            val id = identifierFactory.uniqueIdentifier()
            val team = Team(id)
            teamsStorage[id] = team
            team
        }
        return newTeams
    }

    fun isTeamExists(teamId: Identifier): Boolean {
        return teamsStorage.containsKey(teamId)
    }
}
