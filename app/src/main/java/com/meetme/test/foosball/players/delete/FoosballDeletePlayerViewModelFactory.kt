package com.meetme.test.foosball.players.delete

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.meetme.test.foosball.players.delete.usecase.DeletePlayerUseCase
import com.meetme.test.foosball.players.usecase.GetPlayerUseCase


class FoosballDeletePlayerViewModelFactory(
        private val getPlayerUseCase: GetPlayerUseCase,
        private val deletePlayerUseCase: DeletePlayerUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            FoosballDeletePlayerViewModel(getPlayerUseCase, deletePlayerUseCase) as T
}