package com.meetme.test.foosball.players.add.di

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.meetme.test.foosball.players.add.FoosballAddPlayerViewModelFactory
import com.meetme.test.foosball.players.add.usecase.AddPlayerUseCase


val foosballAddPlayerModule = Kodein.Module {

    bind<AddPlayerUseCase>() with provider { AddPlayerUseCase(instance()) }

    bind<FoosballAddPlayerViewModelFactory>() with provider { FoosballAddPlayerViewModelFactory(instance()) }
}