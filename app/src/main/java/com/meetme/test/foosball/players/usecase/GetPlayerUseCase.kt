package com.meetme.test.foosball.players.usecase

import android.arch.lifecycle.LiveData
import com.meetme.test.base.UseCase
import com.meetme.test.foosball.data.db.FoosballLocalSource
import com.meetme.test.foosball.data.db.entity.Player


class GetPlayerUseCase(private val foosballLocalSource: FoosballLocalSource) : UseCase<Long, LiveData<Player>> {
    override fun execute(input: Long): LiveData<Player> = foosballLocalSource.getPlayer(input)
}