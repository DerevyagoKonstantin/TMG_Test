package com.meetme.test.foosball.update

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.meetme.test.foosball.players.usecase.GetPlayersUseCase
import com.meetme.test.foosball.usecase.GetGameUseCase
import com.meetme.test.foosball.update.usecase.UpdateGameUseCase


class FoosballUpdateGameViewModelFactory(
        private val getPlayersUseCase: GetPlayersUseCase,
        private val getGameUseCase: GetGameUseCase,
        private val updateGameUseCase: UpdateGameUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            FoosballUpdateGameViewModel(getPlayersUseCase, getGameUseCase, updateGameUseCase) as T
}