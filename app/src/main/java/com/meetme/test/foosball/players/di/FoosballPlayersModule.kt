package com.meetme.test.foosball.players.di

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.meetme.test.foosball.players.FoosballPlayersViewModelFactory
import com.meetme.test.foosball.players.usecase.GetPlayersSortUseCase
import com.meetme.test.foosball.players.usecase.GetPlayersWithGamesUseCase
import com.meetme.test.foosball.players.usecase.SavePlayersSortUseCase


val foosballPlayersModule = Kodein.Module {
    bind<GetPlayersWithGamesUseCase>() with provider { GetPlayersWithGamesUseCase(instance()) }

    bind<SavePlayersSortUseCase>() with provider { SavePlayersSortUseCase(instance()) }

    bind<GetPlayersSortUseCase>() with provider { GetPlayersSortUseCase(instance()) }

    bind<FoosballPlayersViewModelFactory>() with provider {
        FoosballPlayersViewModelFactory(instance(), instance(), instance())
    }
}