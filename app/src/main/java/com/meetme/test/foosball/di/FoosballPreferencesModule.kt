package com.meetme.test.foosball.di

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.meetme.test.foosball.data.preferences.FoosballPreferences


val foosballPreferencesModule = Kodein.Module {
    bind<FoosballPreferences>() with provider { FoosballPreferences(instance()) }
}