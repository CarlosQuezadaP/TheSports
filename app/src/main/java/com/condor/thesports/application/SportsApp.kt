package com.condor.thesports.application

import android.app.Application
import com.condor.data.di.*
import com.condor.thesports.di.detailModule
import com.condor.thesports.di.leagueModule
import com.condor.thesports.di.mainModule
import com.condor.usecases.di.usecaseGetAllEvent
import com.condor.usecases.di.usecaseGetAllLeagues
import com.condor.usecases.di.usecaseGetAllTeams
import com.condor.usecases.di.usecaseGetTeam
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin

class SportsApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@SportsApp)
            fragmentFactory()
            modules(
                listOf(
                    mainModule,
                    detailModule,
                    leagueModule,

                    databaseModule,
                    networkModule,
                    converterModule,

                    handlerTeamRepositoriesModule,
                    handlerEventRepositoriesModule,
                    handlerLeagueRepositoriesModule,

                    eventRepositoryModule,
                    leagueRepositoryModule,
                    teamRepositoriesModule,

                    usecaseGetAllTeams,
                    usecaseGetAllEvent,
                    usecaseGetAllLeagues,
                    usecaseGetTeam,
                    )
            )
        }
    }
}