package com.condor.data.di

import com.condor.data.repository.LeagueRemoteRepository
import com.condor.data.repository.LocalLeagueRepository
import com.condor.data.repository.LocalRepository
import com.condor.data.repository.RemoteRepository
import com.condor.data.repositoryImpl.*
import com.condor.domain.models.EventDomain
import com.condor.domain.models.TeamDomain
import org.koin.core.qualifier.named
import org.koin.dsl.module

val teamRepositoriesModule = module {
    single<RemoteRepository<TeamDomain>>(named("remote_team")) { TeamRepositoryImpl(get(), get()) }
    single<LocalRepository<TeamDomain>> (named("local_team")){ TeamLocalDatabaseImpl(get(), get()) }
}

val eventRepositoryModule = module {
    single<RemoteRepository<EventDomain>>(named("remote_event")) { EventRepositoryImpl(get(), get()) }
    single<LocalRepository<EventDomain>> (named("local_event")){ EventLocalDatabaseImpl(get(), get()) }
}

val leagueRepositoryModule = module {
    single<LeagueRemoteRepository> { RemoteRetrofitLeague(get(), get()) }
    single<LocalLeagueRepository> { LocalRoomDatabaseLeague(get(), get()) }
}