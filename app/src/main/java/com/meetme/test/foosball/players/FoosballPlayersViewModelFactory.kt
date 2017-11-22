package com.meetme.test.foosball.players

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.meetme.test.foosball.players.usecase.GetPlayersUseCase


class FoosballPlayersViewModelFactory(
        private val getPlayersUseCase: GetPlayersUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            FoosballPlayersViewModel(getPlayersUseCase) as T
}