package com.meetme.test.foosball.delete

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.meetme.test.foosball.delete.usecase.DeleteGameUseCase


class FoosballDeleteGameViewModelFactory(
        private val deleteGameUseCase: DeleteGameUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            FoosballDeleteGameViewModel(deleteGameUseCase) as T
}