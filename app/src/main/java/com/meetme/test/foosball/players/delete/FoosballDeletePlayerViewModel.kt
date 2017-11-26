package com.meetme.test.foosball.players.delete

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.meetme.test.foosball.players.delete.usecase.DeletePlayerUseCase


class FoosballDeletePlayerViewModel(
        private val deletePlayerUseCase: DeletePlayerUseCase
) : ViewModel() {

    val playerId = MutableLiveData<Long>()

    fun deletePlayer() {
        playerId.value?.let { deletePlayerUseCase.execute(it) }
    }
}