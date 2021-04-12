package com.condor.data.di

import com.condor.data.repository.LeagueRemoteRepository
import com.condor.data.repository.LocalLeagueRepository
import com.condor.data.repository.LocalRepository
import com.condor.data.repository.RemoteRepository
import com.condor.data.datasource.local.LocalDataSourceEvent
import com.condor.data.datasource.local.LocalDataSourceLeague
import com.condor.data.datasource.local.LocalDataSourceTeam
import com.condor.data.datasource.remote.EventRepositoryImpl
import com.condor.data.datasource.remote.RemoteRetrofitLeague
import com.condor.data.datasource.remote.TeamRepositoryImpl
import com.condor.domain.models.EventDomain
import com.condor.domain.models.TeamDomain
import org.koin.core.qualifier.named
import org.koin.dsl.module

val teamRepositoriesModule = module {
    single<RemoteRepository<TeamDomain>>(named("remote_team")) { TeamRepositoryImpl(get(), get()) }
    single<LocalRepository<TeamDomain>> (named("local_team")){ LocalDataSourceTeam(get(), get()) }
}

val eventRepositoryModule = module {
    single<RemoteRepository<EventDomain>>(named("remote_event")) { EventRepositoryImpl(get(), get()) }
    single<LocalRepository<EventDomain>> (named("local_event")){ LocalDataSourceEvent(get(), get()) }
}

val leagueRepositoryModule = module {
    single<LeagueRemoteRepository> { RemoteRetrofitLeague(get(), get()) }
    single<LocalLeagueRepository> { LocalDataSourceLeague(get(), get()) }
}