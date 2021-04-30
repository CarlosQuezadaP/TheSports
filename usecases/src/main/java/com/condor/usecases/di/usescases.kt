package com.condor.usecases.di

import com.condor.usecases.*
import org.koin.dsl.module

val usecaseGetAllTeams = module {
    single<IGetAllTeamsUseCase> {
        GetAllTeamsUseCase(get())
    }
}

val usecaseGetAllEvent = module {
    single<IGetAllEventsUseCase> {
        GetAllEventsUseCase(get())
    }
}

val usecaseGetAllLeagues = module {
    single<IGetAllLeaguesUseCase> {
        GetAllLeaguesUseCase(get())
    }
}

val usecaseGetTeam = module {
    single<IGetTeamUseCase> {
        GetTeamUseCase(get())
    }
}

