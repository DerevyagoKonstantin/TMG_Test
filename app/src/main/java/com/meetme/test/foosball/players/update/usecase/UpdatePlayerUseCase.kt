package com.meetme.test.foosball.players.update.usecase

import com.meetme.test.base.UseCase
import com.meetme.test.foosball.data.db.FoosballLocalSource
import com.meetme.test.foosball.data.db.entity.Player


class UpdatePlayerUseCase(private val foosballLocalSource: FoosballLocalSource) : UseCase<Player, Unit> {
    override fun execute(input: Player) {
        foosballLocalSource.updatePlayer(input)
    }
}