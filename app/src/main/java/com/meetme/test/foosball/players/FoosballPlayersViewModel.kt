package com.meetme.test.foosball.players

import android.arch.lifecycle.*
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
    val players = getPlayersWithGamesUseCase.execute(Unit)
    val sortedPlayers = MediatorLiveData<List<PlayerWithGames>>()

    val addPlayer = MutableLiveData<Unit>()
    val updatePlayer = MutableLiveData<Player>()
    val deletePlayer = MutableLiveData<Player>()

    val emptyVisibility: LiveData<Boolean> = Transformations.map(players, { it.isEmpty() })

    init {
        sort.value = getPlayersSortUseCase.execute(Unit)

        sortedPlayers.addSource(sort, {
            if (it != null) {
                savePlayersSortUseCase.execute(it)
                val players = players.value
                if (players != null) {
                    sortedPlayers.value = getSortedPlayers(it, players)
                }
            }
        })
        sortedPlayers.addSource(players, {
            val sort = sort.value
            if (it != null && sort != null) {
                sortedPlayers.value = getSortedPlayers(sort, it)
            }
        })
    }

    private fun getSortedPlayers(sort: PlayersSort, players: List<PlayerWithGames>): List<PlayerWithGames> {
        return when (sort) {
            PlayersSort.WIN_PERCENTAGE -> {
                Collections.sort(players, { firstPlayer, secondPlayer ->
                    secondPlayer.getWinPercentage().compareTo(firstPlayer.getWinPercentage())
                })
                players
            }
            PlayersSort.GAMES -> {
                Collections.sort(players, { firstPlayer, secondPlayer ->
                    secondPlayer.getGames().compareTo(firstPlayer.getGames())
                })
                players
            }
            PlayersSort.WINS -> {
                Collections.sort(players, { firstPlayer, secondPlayer ->
                    secondPlayer.wins.compareTo(firstPlayer.wins)
                })
                players
            }
            PlayersSort.LOSSES -> {
                Collections.sort(players, { firstPlayer, secondPlayer ->
                    secondPlayer.loses.compareTo(firstPlayer.loses)
                })
                players
            }
            PlayersSort.DRAWS -> {
                Collections.sort(players, { firstPlayer, secondPlayer ->
                    secondPlayer.draws.compareTo(firstPlayer.draws)
                })
                players
            }
        }
    }
}