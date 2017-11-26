package com.meetme.test.foosball.delete.usecase

import com.meetme.test.base.UseCase
import com.meetme.test.foosball.data.db.FoosballLocalSource


class DeleteGameUseCase(private val foosballLocalSource: FoosballLocalSource) : UseCase<Long, Unit> {

    override fun execute(input: Long) {
        foosballLocalSource.deleteGame(input)
    }
}