package com.meetme.test.foosball.add

import android.arch.lifecycle.ViewModel
import com.meetme.test.foosball.add.usecase.AddGameUseCase
import com.meetme.test.foosball.data.db.entity.GameWithPlayers
import com.meetme.test.foosball.players.usecase.GetPlayersUseCase


class FoosballAddGameViewModel(
        getPlayersUseCase: GetPlayersUseCase,
        private val addGameUseCase: AddGameUseCase
) : ViewModel() {

    val players = getPlayersUseCase.execute(Unit)

    fun saveGame(game: GameWithPlayers) {
        addGameUseCase.execute(game.getGame())
    }
}