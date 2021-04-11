package com.condor.usecases.di

import com.condor.usecases.IRetrieveAllTeams
import com.condor.usecases.RetrieveAllTeams
import org.koin.dsl.module

val usecaseRetrieveAllTeams = module {
    single<IRetrieveAllTeams> {
        RetrieveAllTeams(get())
    }
}
