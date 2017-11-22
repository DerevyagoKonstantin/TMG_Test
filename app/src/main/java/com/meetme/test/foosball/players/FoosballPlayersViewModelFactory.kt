package com.meetme.test.foosball.players

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.meetme.test.foosball.players.usecase.DeletePlayerUseCase
import com.meetme.test.foosball.players.usecase.GetPlayersUseCase


class FoosballPlayersViewModelFactory(
        private val deletePlayerUseCase: DeletePlayerUseCase,
        private val getPlayersUseCase: GetPlayersUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = FoosballPlayersViewModel(
            deletePlayerUseCase,
            getPlayersUseCase
    ) as T
}