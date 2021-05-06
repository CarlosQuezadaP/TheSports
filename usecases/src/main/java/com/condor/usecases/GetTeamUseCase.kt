package com.condor.usecases

import com.condor.core.ResultWrapper
import com.condor.domain.models.TeamDomain
import com.condor.usecases.repository.ITeamRepositoryHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTeamUseCase(private val teamRepositoryRepositoryHandler: ITeamRepositoryHandler) :
    IGetTeamUseCase {

    //Todo validacion de funcion
    override fun invoke(id: String): Flow<ResultWrapper<TeamDomain>> {
        return teamRepositoryRepositoryHandler.getById(id).map {
            val response: ResultWrapper<TeamDomain> = when (it) {
                is ResultWrapper.Loading -> {
                    it
                }
                is ResultWrapper.Success -> {
                    ResultWrapper.Success(it.data[0])
                }
                is ResultWrapper.Error -> {
                    it
                }
            }
            response
        }
    }
}

interface IGetTeamUseCase {
    fun invoke(id: String): Flow<ResultWrapper<TeamDomain>>
}