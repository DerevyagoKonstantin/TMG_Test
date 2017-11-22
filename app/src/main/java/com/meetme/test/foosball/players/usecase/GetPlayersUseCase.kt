package com.meetme.test.foosball.players.usecase

import android.arch.lifecycle.LiveData
import com.meetme.test.base.UseCase
import com.meetme.test.foosball.data.db.FoosballLocalSource
import com.meetme.test.foosball.data.db.entity.Player


class GetPlayersUseCase(private val foosballLocalSource: FoosballLocalSource) : UseCase<Unit, LiveData<List<Player>>> {
    override fun execute(input: Unit): LiveData<List<Player>> = foosballLocalSource.getAllPlayers()
}