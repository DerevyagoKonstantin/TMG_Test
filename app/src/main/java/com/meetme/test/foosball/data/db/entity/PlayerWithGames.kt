package com.meetme.test.foosball.data.db.entity

import android.arch.persistence.room.ColumnInfo


const val WINS = "wins"
const val LOSES = "loses"
const val DRAWS = "draws"

data class PlayerWithGames(
        @ColumnInfo(name = PLAYER_ID)
        var id: Long = 0,
        @ColumnInfo(name = FIRST_NAME)
        var firstName: String = "",
        @ColumnInfo(name = LAST_NAME)
        var lastName: String = "",
        @ColumnInfo(name = WINS)
        var wins: Int = 0,
        @ColumnInfo(name = LOSES)
        var loses: Int = 0,
        @ColumnInfo(name = DRAWS)
        var draws: Int = 0
) {
    fun getPlayer() = Player(id, firstName, lastName)

    fun getGamesStat(): String {
        val games = wins + loses + draws
        val winPercentage = if (games == 0) 0 else wins * 100 / games
        return "$wins : $loses : $draws / $winPercentage%"
    }
}