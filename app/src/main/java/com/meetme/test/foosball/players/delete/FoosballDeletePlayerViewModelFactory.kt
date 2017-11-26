package com.meetme.test.foosball.players.delete

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.meetme.test.foosball.players.delete.usecase.DeletePlayerUseCase


class FoosballDeletePlayerViewModelFactory(
        private val deletePlayerUseCase: DeletePlayerUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            FoosballDeletePlayerViewModel(deletePlayerUseCase) as T
}