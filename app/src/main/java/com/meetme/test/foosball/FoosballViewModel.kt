package com.meetme.test.foosball

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.meetme.test.foosball.data.db.entity.Game
import com.meetme.test.foosball.usecase.GetGamesUseCase


class FoosballViewModel(getGamesUseCase: GetGamesUseCase) : ViewModel() {

    val games = getGamesUseCase.execute(Unit)

    val openPlayers = MutableLiveData<Unit>()

    val addGame = MutableLiveData<Unit>()
    val updateGame = MutableLiveData<Game>()
    val deleteGame = MutableLiveData<Game>()
}