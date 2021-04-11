package com.condor.thesports.di

import com.condor.thesports.viewmodels.TeamsListViewModel
import org.koin.dsl.module

val mainModule = module {
    single { TeamsListViewModel(get()) }
}

