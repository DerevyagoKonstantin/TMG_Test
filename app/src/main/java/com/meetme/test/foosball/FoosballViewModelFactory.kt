package com.meetme.test.foosball

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.meetme.test.foosball.usecase.DeleteGameUseCase
import com.meetme.test.foosball.usecase.GetGamesUseCase


class FoosballViewModelFactory(
        private val getGamesUseCase: GetGamesUseCase,
        private val deleteGameUseCase: DeleteGameUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            FoosballViewModel(getGamesUseCase, deleteGameUseCase) as T
}