package com.condor.usecases

import com.condor.core.ResultWrapper
import com.condor.domain.models.LeagueDomain
import com.condor.usecases.repository.ILeagueRepositoryHandler
import kotlinx.coroutines.flow.Flow

class GetAllLeaguesUseCase(private val iLeagueRepositoryHandler: ILeagueRepositoryHandler) :
    IGetAllLeaguesUseCase {
    override fun invoke() = iLeagueRepositoryHandler.getAllLeagues()
}

interface IGetAllLeaguesUseCase {
    fun invoke(): Flow<ResultWrapper<List<LeagueDomain>>>
}
