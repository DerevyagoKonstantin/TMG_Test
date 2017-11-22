package com.meetme.test.foosball.update.di

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.meetme.test.foosball.players.usecase.GetPlayersUseCase
import com.meetme.test.foosball.update.FoosballUpdateGameViewModelFactory
import com.meetme.test.foosball.usecase.GetGameUseCase
import com.meetme.test.foosball.update.usecase.UpdateGameUseCase


val foosballUpdateGameModule = Kodein.Module {
    bind<GetPlayersUseCase>() with provider { GetPlayersUseCase(instance()) }

    bind<GetGameUseCase>() with provider { GetGameUseCase(instance()) }

    bind<UpdateGameUseCase>() with provider { UpdateGameUseCase(instance()) }

    bind<FoosballUpdateGameViewModelFactory>() with provider {
        FoosballUpdateGameViewModelFactory(instance(), instance(), instance())
    }
}