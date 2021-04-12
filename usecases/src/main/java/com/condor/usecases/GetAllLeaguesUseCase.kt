package com.condor.usecases

import com.condor.core.ResultWrapper
import com.condor.data.handler.LeagueRepositoryHandler
import com.condor.domain.models.LeagueDomain
import kotlinx.coroutines.flow.Flow

class GetAllLeaguesUseCase(val repositoryHandler: LeagueRepositoryHandler) : IGetAllLeaguesUseCase {
    override fun invoke() = repositoryHandler.getAllLeagues()
}

interface IGetAllLeaguesUseCase {
    fun invoke(): Flow<ResultWrapper<List<LeagueDomain>>>
}
