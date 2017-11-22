package com.meetme.test.foosball.players.delete

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.meetme.test.foosball.data.db.entity.Player
import com.meetme.test.foosball.players.delete.usecase.DeletePlayerUseCase
import com.meetme.test.foosball.players.usecase.GetPlayerUseCase


class FoosballDeletePlayerViewModel(
        private val getPlayerUseCase: GetPlayerUseCase,
        private val deletePlayerUseCase: DeletePlayerUseCase
) : ViewModel() {

    val playerId = MutableLiveData<Long>()
    var player: LiveData<Player> = Transformations.switchMap(playerId, { getPlayerUseCase.execute(it) })

    val deletePlayer = MutableLiveData<Unit>()
    val deletePlayerObserver: LiveData<Player> = Transformations.switchMap(deletePlayer, {
        player.value?.let { deletePlayerUseCase.execute(it) }
        player
    })
}