package jetbrains.kotlin.course.alias.team

import jetbrains.kotlin.course.alias.util.Identifier
import jetbrains.kotlin.course.alias.util.IdentifierFactory
import org.springframework.stereotype.Service
import java.io.File
import kotlinx.serialization.*
import kotlinx.serialization.json.Json

@Service
class TeamService(
    internal val identifierFactory: IdentifierFactory = IdentifierFactory()
) {

    companion object {
        val teamsStorage: MutableMap<Identifier, Team> = mutableMapOf()

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
    fun saveTeamsStorage(teamsStorage: Map<Identifier, Team>, filePath: String) {
        val json = Json.encodeToString(teamsStorage)
        File(filePath).writeText(json)
    }
    fun loadTeamsStorage(filePath: String): MutableMap<Identifier, Team> {
        val json = File(filePath).readText()
        return Json.decodeFromString(json)
    }
    fun getAllTeams(): List<Team> {
        return teamsStorage.values.toList()
    }


}
