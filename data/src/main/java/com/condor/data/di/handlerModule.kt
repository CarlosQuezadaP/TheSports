package com.condor.data.di

import com.condor.data.handler.EventRepositoryHandler
import com.condor.data.handler.LeagueRepositoryHandler
import com.condor.data.handler.TeamRepositoryHandler
import org.koin.core.qualifier.named
import org.koin.dsl.module

val handlerTeamRepositoriesModule = module {
    single { TeamRepositoryHandler(get((named("local_team"))), get((named("remote_team")))) }
}

val handlerEventRepositoriesModule = module {
    single { EventRepositoryHandler(get((named("local_event"))), get((named("remote_event")))) }
}

val handlerLeagueRepositoriesModule = module {
    single { LeagueRepositoryHandler(get(), get())}
}


