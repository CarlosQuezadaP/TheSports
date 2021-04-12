package com.condor.thesports.application

import android.app.Application
import com.condor.data.di.*
import com.condor.thesports.di.mainModule
import com.condor.usecases.di.usecaseRetrieveAllTeams
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin

class SportsApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@SportsApp)
            fragmentFactory()
            modules(listOf(
                mainModule,
                usecaseRetrieveAllTeams,
                databaseModule,
                eventRepositoryModule,
                leagueRepositoryModule,
                teamRepositoriesModule,
                handlerRepositoriesModule,
                converterModule,
                networkModule
            ))
        }
    }

}