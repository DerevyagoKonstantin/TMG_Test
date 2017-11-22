package com.meetme.test.foosball.usecase

import android.arch.lifecycle.LiveData
import com.meetme.test.base.UseCase
import com.meetme.test.foosball.data.db.FoosballLocalSource
import com.meetme.test.foosball.data.db.entity.GameWithPlayers


class GetGameUseCase(
        private val foosballLocalSource: FoosballLocalSource
) : UseCase<Long, LiveData<GameWithPlayers>> {

    override fun execute(input: Long): LiveData<GameWithPlayers> = foosballLocalSource.getGame(input)
}