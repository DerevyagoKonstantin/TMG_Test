package com.meetme.test.foosball.players

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.meetme.test.foosball.players.usecase.GetPlayersSortUseCase
import com.meetme.test.foosball.players.usecase.GetPlayersWithGamesUseCase
import com.meetme.test.foosball.players.usecase.SavePlayersSortUseCase


class FoosballPlayersViewModelFactory(
        private val getPlayersWithGamesUseCase: GetPlayersWithGamesUseCase,
        private val savePlayersSortUseCase: SavePlayersSortUseCase,
        private val getPlayersSortUseCase: GetPlayersSortUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            FoosballPlayersViewModel(getPlayersWithGamesUseCase, savePlayersSortUseCase, getPlayersSortUseCase) as T
}