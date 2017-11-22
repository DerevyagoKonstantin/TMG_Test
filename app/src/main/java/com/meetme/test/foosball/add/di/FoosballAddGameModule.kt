package com.meetme.test.foosball.add.di

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.meetme.test.foosball.add.FoosballAddGameViewModelFactory
import com.meetme.test.foosball.add.usecase.AddGameUseCase
import com.meetme.test.foosball.players.usecase.GetPlayersUseCase


val foosballAddGameModule = Kodein.Module {
    bind<GetPlayersUseCase>() with provider { GetPlayersUseCase(instance()) }

    bind<AddGameUseCase>() with provider { AddGameUseCase(instance()) }

    bind<FoosballAddGameViewModelFactory>() with provider {
        FoosballAddGameViewModelFactory(instance(), instance())
    }
}