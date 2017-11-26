package com.meetme.test.foosball.players.add

import android.arch.lifecycle.ViewModel
import com.meetme.test.foosball.data.db.entity.Player
import com.meetme.test.foosball.players.add.usecase.AddPlayerUseCase


class FoosballAddPlayerViewModel(private val addPlayerUseCase: AddPlayerUseCase) : ViewModel() {

    fun savePlayer(player: Player) {
        addPlayerUseCase.execute(player)
    }
}