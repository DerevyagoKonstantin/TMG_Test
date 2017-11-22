package com.meetme.test.foosball.players.update.di

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.meetme.test.foosball.players.update.FoosballUpdatePlayerViewModelFactory
import com.meetme.test.foosball.players.usecase.GetPlayerUseCase
import com.meetme.test.foosball.players.update.usecase.UpdatePlayerUseCase


val foosballUpdatePlayerModule = Kodein.Module {
    bind<GetPlayerUseCase>() with provider { GetPlayerUseCase(instance()) }

    bind<UpdatePlayerUseCase>() with provider { UpdatePlayerUseCase(instance()) }

    bind<FoosballUpdatePlayerViewModelFactory>() with provider {
        FoosballUpdatePlayerViewModelFactory(instance(), instance())
    }
}