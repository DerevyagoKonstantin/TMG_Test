package com.meetme.test.foosball.players.delete.di

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.meetme.test.foosball.players.delete.FoosballDeletePlayerViewModelFactory
import com.meetme.test.foosball.players.delete.usecase.DeletePlayerUseCase
import com.meetme.test.foosball.players.usecase.GetPlayerUseCase


val foosballDeletePlayerModule = Kodein.Module {
    bind<GetPlayerUseCase>() with provider { GetPlayerUseCase(instance()) }

    bind<DeletePlayerUseCase>() with provider { DeletePlayerUseCase(instance()) }

    bind<FoosballDeletePlayerViewModelFactory>() with provider {
        FoosballDeletePlayerViewModelFactory(instance(), instance())
    }
}