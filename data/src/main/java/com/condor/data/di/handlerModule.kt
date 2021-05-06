package com.condor.data.di

import com.condor.data.handler.EventRepositoryHandler
import com.condor.data.handler.LeagueRepositoryHandler
import com.condor.data.handler.TeamRepositoryHandler
import com.condor.usecases.repository.IEventRepositoryHandler
import com.condor.usecases.repository.ILeagueRepositoryHandler
import com.condor.usecases.repository.ITeamRepositoryHandler
import org.koin.core.qualifier.named
import org.koin.dsl.module

val handlerTeamRepositoriesModule = module {
    single<ITeamRepositoryHandler> {
        TeamRepositoryHandler(
            get((named("local_team"))),
            get()
        )
    }
}

val handlerEventRepositoriesModule = module {
    single<IEventRepositoryHandler> {
        EventRepositoryHandler(
            get((named("local_event"))),
            get()
        )
    }
}

val handlerLeagueRepositoriesModule = module {
    single<ILeagueRepositoryHandler> {
        LeagueRepositoryHandler(
            get((named("local_league"))),
            get()
        )
    }
}

