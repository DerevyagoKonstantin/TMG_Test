package com.meetme.test.foosball

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.meetme.test.foosball.data.db.entity.Game
import com.meetme.test.foosball.usecase.DeleteGameUseCase
import com.meetme.test.foosball.usecase.GetGamesUseCase


class FoosballViewModel(
        getGamesUseCase: GetGamesUseCase,
        deleteGameUseCase: DeleteGameUseCase
) : ViewModel() {

    val games = getGamesUseCase.execute(Unit)

    val updateGame = MutableLiveData<Game>()

    val deleteGame = MutableLiveData<Game>()
    val deleteGameObserver = Transformations.map(deleteGame, {
        deleteGameUseCase.execute(it)
        it
    })
}