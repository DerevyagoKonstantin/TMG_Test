package com.meetme.test.foosball.players.usecase

import android.arch.lifecycle.LiveData
import com.meetme.test.base.UseCase
import com.meetme.test.foosball.data.db.FoosballLocalSource
import com.meetme.test.foosball.data.db.entity.PlayerWithGames


class GetPlayersWithGamesUseCase(
        private val foosballLocalSource: FoosballLocalSource
) : UseCase<Unit, LiveData<List<PlayerWithGames>>> {

    override fun execute(input: Unit): LiveData<List<PlayerWithGames>> = foosballLocalSource.getPlayersWithGames()
}