package com.condor.data.di

import com.condor.data.datasource.local.DataSourceLocalEvent
import com.condor.data.datasource.local.DataSourceLocalLeague
import com.condor.data.datasource.local.DataSourceLocalTeam
import com.condor.data.datasource.local.ILocalRepository
import com.condor.data.datasource.remote.*
import com.condor.domain.models.EventDomain
import com.condor.domain.models.LeagueDomain
import com.condor.domain.models.TeamDomain
import org.koin.core.qualifier.named
import org.koin.dsl.module

val teamRepositoriesModule = module {
    single<IDataSourceRemoteTeam> { DataSourceRemoteTeam(get(), get()) }
    single<ILocalRepository<TeamDomain>>(named("local_team")) { DataSourceLocalTeam(get(), get()) }
}

val eventRepositoryModule = module {
    single<IDataSourceRemoteEvent> { DataSourceRemoteEvent(get(), get()) }
    single<ILocalRepository<EventDomain>>(named("local_event")) {
        DataSourceLocalEvent(
            get(),
            get()
        )
    }
}

val leagueRepositoryModule = module {
    single<IDataSourceRemoteLeague> { DataSourceRemoteLeague(get(), get()) }
    single<ILocalRepository<LeagueDomain>>(named("local_league")) {
        DataSourceLocalLeague(
            get(),
            get()
        )
    }
}