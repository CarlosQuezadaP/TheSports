package com.condor.usecases

import com.condor.core.ResultWrapper
import com.condor.domain.models.TeamDomain
import com.condor.usecases.repository.ITeamRepositoryHandler
import kotlinx.coroutines.flow.Flow

class GetAllTeamsUseCase(private val iTeamRepositoryHandler: ITeamRepositoryHandler) :
    IGetAllTeamsUseCase {
    override fun invoke(leagueParameter: String) = iTeamRepositoryHandler.getAll(leagueParameter)
}

interface IGetAllTeamsUseCase {
    fun invoke(leagueParameter: String): Flow<ResultWrapper<List<TeamDomain>>>
}