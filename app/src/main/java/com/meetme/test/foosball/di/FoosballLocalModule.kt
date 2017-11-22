package com.meetme.test.foosball.di

import android.arch.persistence.room.Room
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.meetme.test.foosball.data.db.FOOSBALL_DB_NAME
import com.meetme.test.foosball.data.db.FoosballDatabase
import com.meetme.test.foosball.data.db.FoosballLocalSource


val foosballLocaleModule = Kodein.Module {
    bind<FoosballLocalSource>() with singleton {
        Room.databaseBuilder(instance(), FoosballDatabase::class.java, FOOSBALL_DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}