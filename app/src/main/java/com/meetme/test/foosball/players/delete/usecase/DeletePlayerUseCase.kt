package com.meetme.test.foosball.players.delete.usecase

import com.meetme.test.base.UseCase
import com.meetme.test.foosball.data.db.FoosballLocalSource


class DeletePlayerUseCase(private val foosballLocalSource: FoosballLocalSource) : UseCase<Long, Unit> {
    override fun execute(input: Long) {
        foosballLocalSource.deletePlayer(input)
    }
}