package com.meetme.test.foosball.delete

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.meetme.test.foosball.delete.usecase.DeleteGameUseCase
import com.meetme.test.foosball.usecase.GetGameUseCase


class FoosballDeleteGameViewModelFactory(
        private val getGameUseCase: GetGameUseCase,
        private val deleteGameUseCase: DeleteGameUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            FoosballDeleteGameViewModel(getGameUseCase, deleteGameUseCase) as T
}