package com.meetme.test.foosball.players.add

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.meetme.test.foosball.players.add.usecase.AddPlayerUseCase


class FoosballAddPlayerViewModelFactory(private val addPlayerUseCase: AddPlayerUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = FoosballAddPlayerViewModel(addPlayerUseCase) as T
}