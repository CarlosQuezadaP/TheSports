package com.condor.data.di

import com.condor.data.repository.ILeagueRemoteRepository
import com.condor.data.repository.ILeagueLocalRepository
import com.condor.data.repository.ILocalRepository
import com.condor.data.repository.IRemoteRepository
import com.condor.data.datasource.local.DataSourceLocalEvent
import com.condor.data.datasource.local.DataSourceLocalLeague
import com.condor.data.datasource.local.DataSourceLocalTeam
import com.condor.data.datasource.remote.DataSourceRemoteEvent
import com.condor.data.datasource.remote.DataSourceRemoteLeague
import com.condor.data.datasource.remote.DataSourceRemoteTeam
import com.condor.domain.models.EventDomain
import com.condor.domain.models.TeamDomain
import org.koin.core.qualifier.named
import org.koin.dsl.module

val teamRepositoriesModule = module {
    single<IRemoteRepository<TeamDomain>>(named("remote_team")) { DataSourceRemoteTeam(get(), get()) }
    single<ILocalRepository<TeamDomain>> (named("local_team")){ DataSourceLocalTeam(get(), get()) }
}

val eventRepositoryModule = module {
    single<IRemoteRepository<EventDomain>>(named("remote_event")) { DataSourceRemoteEvent(get(), get()) }
    single<ILocalRepository<EventDomain>> (named("local_event")){ DataSourceLocalEvent(get(), get()) }
}

val leagueRepositoryModule = module {
    single<ILeagueRemoteRepository> { DataSourceRemoteLeague(get(), get()) }
    single<ILeagueLocalRepository> { DataSourceLocalLeague(get(), get()) }
}