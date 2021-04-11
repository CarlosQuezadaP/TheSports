package com.condor.data.di

import androidx.room.Room
import com.condor.data.db.TheSportsDB
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(androidApplication(), TheSportsDB::class.java, "db")
            .allowMainThreadQueries()
            .build()
    }

    single { get<TheSportsDB>().teamDao() }
    single { get<TheSportsDB>().eventDao() }
    single { get<TheSportsDB>().leagueDao() }

}

