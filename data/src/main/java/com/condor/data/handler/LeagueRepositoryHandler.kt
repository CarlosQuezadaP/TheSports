package com.condor.data.handler

import com.condor.core.ResultWrapper
import com.condor.data.repository.ILeagueLocalRepository
import com.condor.data.repository.ILeagueRemoteRepository
import com.condor.domain.models.LeagueDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class LeagueRepositoryHandler constructor(
    val iLeagueLocalRepository: ILeagueLocalRepository,
    val iLeagueRemoteRepository: ILeagueRemoteRepository
) {

    private fun getAllLeaguesToFlow(): Flow<List<LeagueDomain>> {
        return flow {
            val response: List<LeagueDomain> = iLeagueRemoteRepository.getAllLeagues()
            iLeagueLocalRepository.saveLeagues(response)
            emit(response)
        }
    }

    fun getAllLeagues(): Flow<ResultWrapper<List<LeagueDomain>>> {
        return iLeagueLocalRepository.getLeagues().flatMapLatest { leagues: List<LeagueDomain> ->
            if (leagues.isNotEmpty()) {
                flowOf(leagues)
            } else {
                getAllLeaguesToFlow()
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