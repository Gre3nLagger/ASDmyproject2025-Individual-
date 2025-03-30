package jetbrains.kotlin.course.alias.team

import jetbrains.kotlin.course.alias.util.Identifier

// Data class to represent a team with unique ID, points, and name.
// - id: Identifier type for team identification.
// - points: Int to store game points, default value is 0.
// - name: Automatically initializes as "Team#${id + 1}" for leaderboard display.

data class Team(
    val id: Identifier,
    var points: Int = 0
) {
    val name: String = "Team#${id + 1}"
}
