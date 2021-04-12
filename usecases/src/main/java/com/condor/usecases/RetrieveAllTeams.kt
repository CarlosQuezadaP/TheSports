package com.condor.usecases

import com.condor.core.ResultWrapper
import com.condor.data.handler.TeamRepositoryHandler
import com.condor.domain.models.TeamDomain
import kotlinx.coroutines.flow.Flow

class RetrieveAllTeams(private val teamRepositoryHandler: TeamRepositoryHandler) :
    IRetrieveAllTeams {

    override fun invoke(leagueParameter: String): Flow<ResultWrapper<List<TeamDomain>>> {
        return teamRepositoryHandler.getAll(leagueParameter)
    }

}
