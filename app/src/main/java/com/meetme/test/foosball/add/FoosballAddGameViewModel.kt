package com.meetme.test.foosball.add

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.meetme.test.foosball.add.usecase.AddGameUseCase
import com.meetme.test.foosball.data.db.entity.Game
import com.meetme.test.foosball.players.usecase.GetPlayersUseCase


class FoosballAddGameViewModel(
        getPlayersUseCase: GetPlayersUseCase,
        private val addGameUseCase: AddGameUseCase
) : ViewModel() {

    val players = getPlayersUseCase.execute(Unit)

    val saveGame = MutableLiveData<Game>()
    val saveGameObserver: LiveData<Game> = Transformations.map(saveGame, {
        addGameUseCase.execute(it)
        it
    })
}