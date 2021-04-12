package com.condor.data.handler

import com.condor.core.ResultWrapper
import com.condor.data.repository.LeagueRemoteRepository
import com.condor.data.repository.LocalLeagueRepository
import com.condor.domain.models.LeagueDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class LeagueRepositoryHandler constructor(
    val localLeagueRepository: LocalLeagueRepository,
    val remoteRepository: LeagueRemoteRepository
) {

    private fun toFlowRetrieveAllLeagues(): Flow<List<LeagueDomain>> {
        return flow {
            val response: List<LeagueDomain> = remoteRepository.retrieveAllLeagues()
            localLeagueRepository.saveLeagues(response)
            emit(response)
        }
    }

    fun retrieveAllLeagues(): Flow<ResultWrapper<List<LeagueDomain>>> {
        return localLeagueRepository.getLeagues().flatMapLatest { leagues: List<LeagueDomain> ->
            if (leagues.isNotEmpty()) {
                flowOf(leagues)
            } else {
                toFlowRetrieveAllLeagues()
            }
        }.map { leagues: List<LeagueDomain> ->
            val result: ResultWrapper<List<LeagueDomain>> = ResultWrapper.Success(leagues)
            result
        }.onStart {
            emit(ResultWrapper.Loading)
        }.catch {
            emit(ResultWrapper.Error("League error"))
        }.flowOn(Dispatchers.IO)

    }
}