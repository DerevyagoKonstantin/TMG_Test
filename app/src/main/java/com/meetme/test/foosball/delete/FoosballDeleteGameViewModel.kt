package com.meetme.test.foosball.delete

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.meetme.test.foosball.data.db.entity.GameWithPlayers
import com.meetme.test.foosball.delete.usecase.DeleteGameUseCase
import com.meetme.test.foosball.usecase.GetGameUseCase


class FoosballDeleteGameViewModel(
        private val getGameUseCase: GetGameUseCase,
        private val deleteGameUseCase: DeleteGameUseCase
) : ViewModel() {

    val gameId = MutableLiveData<Long>()
    var game: LiveData<GameWithPlayers> = Transformations.switchMap(gameId, {
        getGameUseCase.execute(it)
    })

    val deleteGame = MutableLiveData<Unit>()
    val deleteGameObserver: LiveData<GameWithPlayers> = Transformations.switchMap(deleteGame, {
        game.value?.getGame()?.let { deleteGameUseCase.execute(it) }
        game
    })

}