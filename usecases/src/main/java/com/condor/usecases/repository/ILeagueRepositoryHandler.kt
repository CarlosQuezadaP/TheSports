package com.condor.usecases.repository

import com.condor.core.ResultWrapper
import com.condor.domain.models.LeagueDomain
import kotlinx.coroutines.flow.Flow

interface ILeagueRepositoryHandler {
    fun getAllLeagues(): Flow<ResultWrapper<List<LeagueDomain>>>
}
