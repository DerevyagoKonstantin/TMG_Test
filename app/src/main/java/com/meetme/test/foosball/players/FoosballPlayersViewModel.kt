package com.meetme.test.foosball.players

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.meetme.test.foosball.data.db.entity.Player
import com.meetme.test.foosball.players.usecase.GetPlayersUseCase
import com.meetme.test.foosball.players.usecase.GetPlayersWithGamesUseCase


class FoosballPlayersViewModel(getPlayersUseCase: GetPlayersUseCase, private val getPlayersWithGamesUseCase: GetPlayersWithGamesUseCase) : ViewModel() {

    val players = getPlayersUseCase.execute(Unit)

    val addPlayer = MutableLiveData<Unit>()
    val updatePlayer = MutableLiveData<Player>()
    val deletePlayer = MutableLiveData<Player>()

    val emptyVisibility: LiveData<Boolean> = Transformations.map(players, { it.isEmpty() })

    val playersWithGames = getPlayersWithGamesUseCase.execute(Unit)
}