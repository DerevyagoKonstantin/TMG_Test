package com.meetme.test.foosball.add

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.meetme.test.foosball.add.usecase.AddGameUseCase
import com.meetme.test.foosball.players.usecase.GetPlayersUseCase


class FoosballAddGameViewModelFactory(
        private val getPlayersUseCase: GetPlayersUseCase,
        private val addGameUseCase: AddGameUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            FoosballAddGameViewModel(getPlayersUseCase, addGameUseCase) as T
}