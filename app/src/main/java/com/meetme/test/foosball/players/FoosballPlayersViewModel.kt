package com.meetme.test.foosball.players

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.meetme.test.foosball.data.db.entity.Player
import com.meetme.test.foosball.players.usecase.DeletePlayerUseCase
import com.meetme.test.foosball.players.usecase.GetPlayersUseCase


class FoosballPlayersViewModel(
        private val deletePlayerUseCase: DeletePlayerUseCase,
        getPlayersUseCase: GetPlayersUseCase
) : ViewModel() {

    val players = getPlayersUseCase.execute(Unit)

    val updatePlayer = MutableLiveData<Player>()

    val deletePlayer = MutableLiveData<Player>()
    val deletePlayerObserver: LiveData<Player> = Transformations.map(deletePlayer, {
        deletePlayerUseCase.execute(it)
        it
    })
}