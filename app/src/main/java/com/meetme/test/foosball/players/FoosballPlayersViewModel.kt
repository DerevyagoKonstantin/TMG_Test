package com.meetme.test.foosball.players

import android.arch.lifecycle.*
import android.text.TextUtils
import com.meetme.test.foosball.data.db.entity.Player
import com.meetme.test.foosball.data.db.entity.PlayerWithGames
import com.meetme.test.foosball.players.entity.PlayersSort
import com.meetme.test.foosball.players.usecase.GetPlayersSortUseCase
import com.meetme.test.foosball.players.usecase.GetPlayersWithGamesUseCase
import com.meetme.test.foosball.players.usecase.SavePlayersSortUseCase
import java.util.*


class FoosballPlayersViewModel(
        getPlayersWithGamesUseCase: GetPlayersWithGamesUseCase,
        private val savePlayersSortUseCase: SavePlayersSortUseCase,
        getPlayersSortUseCase: GetPlayersSortUseCase
) : ViewModel() {

    val sort = MutableLiveData<PlayersSort>()
    val search = MutableLiveData<String>()
    private val players = getPlayersWithGamesUseCase.execute(Unit)
    val sortedPlayers = MediatorLiveData<List<PlayerWithGames>>()

    val addPlayer = MutableLiveData<Unit>()
    val updatePlayer = MutableLiveData<Player>()
    val deletePlayer = MutableLiveData<Player>()

    val emptyVisibility: LiveData<Boolean> = Transformations.map(sortedPlayers, { it.isEmpty() })

    init {
        sort.value = getPlayersSortUseCase.execute(Unit)

        sortedPlayers.addSource(sort, {
            if (it != null) {
                savePlayersSortUseCase.execute(it)
                val players = players.value
                val search = search.value ?: ""
                if (players != null) {
                    sortedPlayers.value = getSortedPlayers(it, search, players)
                }
            }
        })
        sortedPlayers.addSource(search, {
            val sort = sort.value
            val search = it ?: ""
            val players = players.value
            if (sort != null && players != null) {
                sortedPlayers.value = getSortedPlayers(sort, search, players)
            }
        })
        sortedPlayers.addSource(players, {
            val sort = sort.value
            val search = search.value ?: ""
            if (it != null && sort != null) {
                sortedPlayers.value = getSortedPlayers(sort, search, it)
            }
        })
    }

    private fun getSortedPlayers(sort: PlayersSort, search: String, players: List<PlayerWithGames>): List<PlayerWithGames> {
        val searchPlayers = getSearchPlayers(search, players)
        return when (sort) {
            PlayersSort.WIN_PERCENTAGE -> {
                Collections.sort(searchPlayers, { firstPlayer, secondPlayer ->
                    secondPlayer.getWinPercentage().compareTo(firstPlayer.getWinPercentage())
                })
                searchPlayers
            }
            PlayersSort.GAMES -> {
                Collections.sort(searchPlayers, { firstPlayer, secondPlayer ->
                    secondPlayer.getGames().compareTo(firstPlayer.getGames())
                })
                searchPlayers
            }
            PlayersSort.WINS -> {
                Collections.sort(searchPlayers, { firstPlayer, secondPlayer ->
                    secondPlayer.wins.compareTo(firstPlayer.wins)
                })
                searchPlayers
            }
            PlayersSort.LOSSES -> {
                Collections.sort(searchPlayers, { firstPlayer, secondPlayer ->
                    secondPlayer.loses.compareTo(firstPlayer.loses)
                })
                searchPlayers
            }
            PlayersSort.DRAWS -> {
                Collections.sort(searchPlayers, { firstPlayer, secondPlayer ->
                    secondPlayer.draws.compareTo(firstPlayer.draws)
                })
                searchPlayers
            }
        }
    }

    private fun getSearchPlayers(search: String, players: List<PlayerWithGames>): List<PlayerWithGames> {
        if (TextUtils.isEmpty(search)) {
            return players
        }

        val searchPlayers = mutableListOf<PlayerWithGames>()
        players.forEach {
            if (it.getPlayer().toString().toLowerCase().contains(search.trim().toLowerCase())) {
                searchPlayers.add(it)
            }
        }
        return searchPlayers
    }
}