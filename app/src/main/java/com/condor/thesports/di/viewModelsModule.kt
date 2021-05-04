package com.condor.thesports.di

import com.condor.thesports.utils.ToastUtils
import com.condor.thesports.viewmodels.LeagueViewModel
import com.condor.thesports.viewmodels.TeamDetailViewModel
import com.condor.thesports.viewmodels.TeamsListViewModel
import org.koin.dsl.module

val mainModule = module {
    single { TeamsListViewModel(get()) }
}

val detailModule = module {
    single { TeamDetailViewModel(get(), get()) }
}

val leagueModule = module {
    single { LeagueViewModel(get()) }
}

val toastModule = module {
    single { ToastUtils(get()) }
}






