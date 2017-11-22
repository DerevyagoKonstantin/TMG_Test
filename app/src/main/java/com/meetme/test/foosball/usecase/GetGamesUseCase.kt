package com.meetme.test.foosball.usecase

import android.arch.lifecycle.LiveData
import com.meetme.test.base.UseCase
import com.meetme.test.foosball.data.db.FoosballLocalSource
import com.meetme.test.foosball.data.db.entity.GameWithPlayers


class GetGamesUseCase(private val foosballLocalSource: FoosballLocalSource) : UseCase<Unit, LiveData<List<GameWithPlayers>>> {
    override fun execute(input: Unit) = foosballLocalSource.getAllGames()
}