package com.meetme.test.foosball.di

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.meetme.test.foosball.FoosballViewModelFactory
import com.meetme.test.foosball.usecase.GetGamesUseCase

/**
 * Created by Konstantin on 17.11.2017.
 */

val foosballModule = Kodein.Module {
    bind<GetGamesUseCase>() with provider { GetGamesUseCase(instance()) }

    bind<FoosballViewModelFactory>() with provider { FoosballViewModelFactory(instance()) }

}