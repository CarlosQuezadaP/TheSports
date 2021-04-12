package com.condor.usecases

import com.condor.core.ResultWrapper
import com.condor.data.handler.TeamRepositoryHandler
import com.condor.domain.models.TeamDomain
import kotlinx.coroutines.flow.Flow

class GetAllTeamsUseCase(private val teamRepositoryHandler: TeamRepositoryHandler) :
    IGetAllTeamsUseCase {
    override fun invoke(leagueParameter: String) = teamRepositoryHandler.getAll(leagueParameter)
}

interface IGetAllTeamsUseCase {
    fun invoke(leagueParameter: String): Flow<ResultWrapper<List<TeamDomain>>>
}