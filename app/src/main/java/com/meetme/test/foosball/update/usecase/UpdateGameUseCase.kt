package com.meetme.test.foosball.update.usecase

import com.meetme.test.base.UseCase
import com.meetme.test.foosball.data.db.FoosballLocalSource
import com.meetme.test.foosball.data.db.entity.Game


class UpdateGameUseCase(private val foosballLocalSource: FoosballLocalSource) : UseCase<Game, Unit> {
    override fun execute(input: Game) {
        foosballLocalSource.updateGame(input)
    }
}