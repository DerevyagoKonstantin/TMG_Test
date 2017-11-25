package com.meetme.test.foosball.players.usecase

import com.meetme.test.base.UseCase
import com.meetme.test.foosball.data.preferences.FoosballPreferences
import com.meetme.test.foosball.players.entity.PlayersSort


class GetPlayersSortUseCase(private val preferences: FoosballPreferences) : UseCase<Unit, PlayersSort> {
    override fun execute(input: Unit) = preferences.getPlayersSort()
}