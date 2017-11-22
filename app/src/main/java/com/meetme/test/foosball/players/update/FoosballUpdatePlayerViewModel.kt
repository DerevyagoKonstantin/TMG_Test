package com.meetme.test.foosball.players.update

import android.arch.lifecycle.*
import com.meetme.test.foosball.data.db.entity.Player
import com.meetme.test.foosball.players.update.usecase.GetPlayerUseCase
import com.meetme.test.foosball.players.update.usecase.UpdatePlayerUseCase


class FoosballUpdatePlayerViewModel(
        private val updatePlayerUseCase: UpdatePlayerUseCase,
        private val getPlayerUseCase: GetPlayerUseCase
) : ViewModel() {

    val playerId = MutableLiveData<Long>()
    var player = Transformations.switchMap(playerId, { getPlayerUseCase.execute(it) })

    val updatePlayer = MutableLiveData<Player>()
    val updatePlayerObserver = MediatorLiveData<Player>()

    init {
        updatePlayerObserver.addSource(updatePlayer, { player ->
            player?.let { updatePlayerUseCase.execute(it) }
            updatePlayerObserver.value = player
        })
    }
}