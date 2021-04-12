package com.condor.usecases

import com.condor.core.ResultWrapper
import com.condor.domain.models.TeamDomain
import kotlinx.coroutines.flow.Flow

interface IRetrieveAllTeams {
    fun invoke(leagueParameter: String): Flow<ResultWrapper<List<TeamDomain>>>
}