package com.meetme.test.foosball.players.add

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.meetme.test.foosball.data.db.entity.Player
import com.meetme.test.foosball.players.add.usecase.AddPlayerUseCase


class FoosballAddPlayerViewModel(private val addPlayerUseCase: AddPlayerUseCase) : ViewModel() {

    val savePlayer = MutableLiveData<Player>()
    val savePlayerObserver: LiveData<Player> = Transformations.map(savePlayer, {
        addPlayerUseCase.execute(it)
        it
    })
}