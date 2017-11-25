package com.meetme.test.foosball

import android.arch.lifecycle.*
import android.text.TextUtils
import com.meetme.test.foosball.data.db.entity.Game
import com.meetme.test.foosball.data.db.entity.GameWithPlayers
import com.meetme.test.foosball.usecase.GetGamesUseCase


class FoosballViewModel(getGamesUseCase: GetGamesUseCase) : ViewModel() {

    val search = MutableLiveData<String>()
    private val games = getGamesUseCase.execute(Unit)
    val sortedGames = MediatorLiveData<List<GameWithPlayers>>()

    val openPlayers = MutableLiveData<Unit>()

    val addGame = MutableLiveData<Unit>()
    val updateGame = MutableLiveData<Game>()
    val deleteGame = MutableLiveData<Game>()

    val emptyVisibility: LiveData<Boolean> = Transformations.map(sortedGames, { it.isEmpty() })

    init {
        sortedGames.addSource(search, {
            val search = it ?: ""
            val games = games.value
            if (games != null) {
                sortedGames.value = getSortedGames(search, games)
            }
        })

        sortedGames.addSource(games, {
            val search = search.value ?: ""
            if (it != null) {
                sortedGames.value = getSortedGames(search, it)
            }
        })
    }

    private fun getSortedGames(search: String, games: List<GameWithPlayers>): List<GameWithPlayers> {
        if (TextUtils.isEmpty(search)) {
            return games
        }

        val sortedGames = mutableListOf<GameWithPlayers>()
        games.forEach {
            if ((it.getFirstPlayer().toString() + " " + it.getSecondPlayer().toString())
                    .toLowerCase().contains(search.toLowerCase())) {
                sortedGames.add(it)
            }
        }
        return sortedGames
    }
}