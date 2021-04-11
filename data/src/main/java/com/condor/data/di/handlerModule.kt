package com.condor.data.di

import com.condor.data.handler.TeamRepositoryHandler
import org.koin.core.qualifier.named
import org.koin.dsl.module


val handlerRepositoriesModule = module {
    single { TeamRepositoryHandler(get((named("local_team"))), get((named("remote_team")))) }
}