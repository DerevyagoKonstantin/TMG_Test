package com.meetme.test.foosball.players.update

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.meetme.test.foosball.data.db.entity.Player
import com.meetme.test.foosball.players.usecase.GetPlayerUseCase
import com.meetme.test.foosball.players.update.usecase.UpdatePlayerUseCase


class FoosballUpdatePlayerViewModel(
        private val updatePlayerUseCase: UpdatePlayerUseCase,
        private val getPlayerUseCase: GetPlayerUseCase
) : ViewModel() {

    val playerId = MutableLiveData<Long>()
    var player: LiveData<Player> = Transformations.switchMap(playerId, { getPlayerUseCase.execute(it) })

    val updatePlayer = MutableLiveData<Player>()
    val updatePlayerObserver: LiveData<Player> = Transformations.map(updatePlayer, {
        updatePlayerUseCase.execute(it)
        it
    })
}