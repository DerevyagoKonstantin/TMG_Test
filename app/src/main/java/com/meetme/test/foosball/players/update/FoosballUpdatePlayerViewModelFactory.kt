package com.meetme.test.foosball.players.update

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.meetme.test.foosball.players.update.usecase.GetPlayerUseCase
import com.meetme.test.foosball.players.update.usecase.UpdatePlayerUseCase


class FoosballUpdatePlayerViewModelFactory(
        private val updatePlayerUseCase: UpdatePlayerUseCase,
        private val getPlayerUseCase: GetPlayerUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            FoosballUpdatePlayerViewModel(updatePlayerUseCase, getPlayerUseCase) as T
}