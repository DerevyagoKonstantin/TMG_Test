package com.meetme.test.foosball.delete

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.meetme.test.foosball.delete.usecase.DeleteGameUseCase


class FoosballDeleteGameViewModel(
        private val deleteGameUseCase: DeleteGameUseCase
) : ViewModel() {

    val gameId = MutableLiveData<Long>()

    fun deleteGame() {
        gameId.value?.let { deleteGameUseCase.execute(it) }
    }

}