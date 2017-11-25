package com.meetme.test.foosball.players.usecase

import com.meetme.test.base.UseCase
import com.meetme.test.foosball.data.preferences.FoosballPreferences
import com.meetme.test.foosball.players.entity.PlayersSort


class SavePlayersSortUseCase(private val preferences: FoosballPreferences) : UseCase<PlayersSort, Unit> {
    override fun execute(input: PlayersSort) {
        preferences.savePlayersSort(input)
    }
}