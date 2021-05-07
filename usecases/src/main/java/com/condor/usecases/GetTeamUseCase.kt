package com.condor.usecases

import com.condor.core.ResultWrapper
import com.condor.domain.models.TeamDomain
import com.condor.usecases.repository.ITeamRepositoryHandler
import kotlinx.coroutines.flow.Flow

class GetTeamUseCase(private val teamRepositoryRepositoryHandler: ITeamRepositoryHandler) :
    IGetTeamUseCase {

    override fun invoke(id: String): Flow<ResultWrapper<TeamDomain>> {
        return teamRepositoryRepositoryHandler.getById(id)
    }
}

interface IGetTeamUseCase {
    fun invoke(id: String): Flow<ResultWrapper<TeamDomain>>
}