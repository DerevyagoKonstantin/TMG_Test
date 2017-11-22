package com.meetme.test.foosball.update

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.meetme.test.foosball.data.db.entity.GameWithPlayers
import com.meetme.test.foosball.players.usecase.GetPlayersUseCase
import com.meetme.test.foosball.update.usecase.UpdateGameUseCase
import com.meetme.test.foosball.usecase.GetGameUseCase


class FoosballUpdateGameViewModel(
        getPlayersUseCase: GetPlayersUseCase,
        private val getGameUseCase: GetGameUseCase,
        private val updateGameUseCase: UpdateGameUseCase
) : ViewModel() {

    val players = getPlayersUseCase.execute(Unit)

    val gameId = MutableLiveData<Long>()
    var game: LiveData<GameWithPlayers> = Transformations.switchMap(gameId, {
        getGameUseCase.execute(it)
    })

    val updateGame = MutableLiveData<GameWithPlayers>()
    val updateGameObserver: LiveData<GameWithPlayers> = Transformations.map(updateGame, {
        updateGameUseCase.execute(it.getGame())
        it
    })

}