package com.meetme.test.foosball.players.di

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.meetme.test.foosball.players.FoosballPlayersViewModelFactory
import com.meetme.test.foosball.players.usecase.GetPlayersWithGamesUseCase


val foosballPlayersModule = Kodein.Module {
    bind<GetPlayersWithGamesUseCase>() with provider { GetPlayersWithGamesUseCase(instance()) }

    bind<FoosballPlayersViewModelFactory>() with provider {
        FoosballPlayersViewModelFactory(instance())
    }
}