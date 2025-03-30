package jetbrains.kotlin.course.alias.team

import jetbrains.kotlin.course.alias.util.Identifier
import jetbrains.kotlin.course.alias.util.IdentifierFactory
import org.springframework.stereotype.Service
import java.io.File
import kotlinx.serialization.*
import kotlinx.serialization.json.Json

@Service
//identifierFactory with the type IdentifierFactory to generate identifiers for each team
class TeamService(
    private val identifierFactory: IdentifierFactory = IdentifierFactory()
) {
    //companion object to the TeamService class and declare the teamsStorage variable to store all previous teams.
    companion object {
        // The storage type should be MutableMap, which maps Identifier to Team.
        // initialize it via an empty map.
        val teamsStorage: MutableMap<Identifier, Team> = mutableMapOf()
        val allTeams: Map<Identifier, Team> get() = teamsStorage
    }

    //generateTeamsForOneRound method to generate a list of teams and also store all of them in the teamsStorage map.
    fun generateTeamsForOneRound(teamsNumber: Int): List<Team> {
        val newTeams = List(teamsNumber) {
            //create new teams using identifierFactory from the TeamService class to generate a new ID.
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
    fun restoreTeams(teams: List<Team>) {
        teamsStorage.clear()
        teams.forEach { teamsStorage[it.id] = it }
    }



}
