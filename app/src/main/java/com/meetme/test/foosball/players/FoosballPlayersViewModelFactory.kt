package com.meetme.test.foosball.players

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.meetme.test.foosball.players.usecase.GetPlayersUseCase
import com.meetme.test.foosball.players.usecase.GetPlayersWithGamesUseCase


class FoosballPlayersViewModelFactory(
        private val getPlayersUseCase: GetPlayersUseCase,
        private val getPlayersWithGamesUseCase: GetPlayersWithGamesUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            FoosballPlayersViewModel(getPlayersUseCase, getPlayersWithGamesUseCase) as T
}