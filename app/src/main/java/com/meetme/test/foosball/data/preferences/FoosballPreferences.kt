package com.meetme.test.foosball.data.preferences

import android.content.SharedPreferences
import com.meetme.test.foosball.players.entity.PlayersSort


const val PLAYERS_SORT = "players_sort"

class FoosballPreferences(private val preferences: SharedPreferences) {

    fun savePlayersSort(sort: PlayersSort) {
        preferences.edit().apply {
            putString(PLAYERS_SORT, sort.toString())
        }.apply()
    }

    fun getPlayersSort(): PlayersSort = PlayersSort.valueOf(
            preferences.getString(PLAYERS_SORT, PlayersSort.WIN_PERCENTAGE.toString())
    )
}