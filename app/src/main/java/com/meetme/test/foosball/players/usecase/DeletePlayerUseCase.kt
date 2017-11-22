package com.meetme.test.foosball.players.usecase

import com.meetme.test.base.UseCase
import com.meetme.test.foosball.data.db.FoosballLocalSource
import com.meetme.test.foosball.data.db.entity.Player


class DeletePlayerUseCase(private val foosballLocalSource: FoosballLocalSource) : UseCase<Player, Unit> {
    override fun execute(input: Player) {
        foosballLocalSource.deletePlayer(input)
    }
}