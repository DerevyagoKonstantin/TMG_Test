package com.meetme.test.foosball.delete.di

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.meetme.test.foosball.delete.FoosballDeleteGameViewModelFactory
import com.meetme.test.foosball.delete.usecase.DeleteGameUseCase
import com.meetme.test.foosball.usecase.GetGameUseCase


val foosballDeleteGameModule = Kodein.Module {
    bind<DeleteGameUseCase>() with provider { DeleteGameUseCase(instance()) }

    bind<GetGameUseCase>() with provider { GetGameUseCase(instance()) }

    bind<FoosballDeleteGameViewModelFactory>() with provider {
        FoosballDeleteGameViewModelFactory(instance(), instance())
    }
}