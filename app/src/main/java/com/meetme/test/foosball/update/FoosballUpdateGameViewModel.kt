package com.meetme.test.foosball.update

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.meetme.test.foosball.data.db.entity.Game
import com.meetme.test.foosball.players.usecase.GetPlayersUseCase
import com.meetme.test.foosball.update.usecase.GetGameUseCase
import com.meetme.test.foosball.update.usecase.UpdateGameUseCase


class FoosballUpdateGameViewModel(
        getPlayersUseCase: GetPlayersUseCase,
        private val getGameUseCase: GetGameUseCase,
        private val updateGameUseCase: UpdateGameUseCase
) : ViewModel() {

    val players = getPlayersUseCase.execute(Unit)

    val gameId = MutableLiveData<Long>()
    var game = Transformations.switchMap(gameId, { getGameUseCase.execute(it) })

    val updateGame = MutableLiveData<Game>()
    val updateGameObserver: LiveData<Game> = Transformations.map(updateGame, {
        updateGameUseCase.execute(it)
        it
    })

}