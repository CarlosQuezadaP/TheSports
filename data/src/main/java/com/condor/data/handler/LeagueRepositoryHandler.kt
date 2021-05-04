package com.condor.data.handler

import com.condor.core.ResultWrapper
import com.condor.data.base.BaseRepositoryHandler
import com.condor.data.datasource.remote.IDataSourceRemoteLeague
import com.condor.usecases.repository.ILeagueRepositoryHandler
import com.condor.data.datasource.local.ILocalRepository
import com.condor.domain.models.LeagueDomain
import kotlinx.coroutines.flow.*

class LeagueRepositoryHandler constructor(
    val iLeagueLocalRepository: ILocalRepository<LeagueDomain>,
    val iDataSourceRemoteLeague: IDataSourceRemoteLeague
) : BaseRepositoryHandler<LeagueDomain>(iLeagueLocalRepository), ILeagueRepositoryHandler {

    private fun getAllLeaguesToFlow(): Flow<List<LeagueDomain>> {
        return flow {
            val response: List<LeagueDomain> = iDataSourceRemoteLeague.getAllLeagues()
            iLeagueLocalRepository.saveAll(response)
            emit(response)
        }
    }

    override fun getAllLeagues(): Flow<ResultWrapper<List<LeagueDomain>>> {
        return iLeagueLocalRepository.getAll("").flatMapLatest { leagues: List<LeagueDomain> ->
            if (leagues.isNotEmpty()) {
                flowOf(leagues)
            } else {
                getAllLeaguesToFlow()
            }
        }.map { leagues: List<LeagueDomain> ->
            val result: ResultWrapper<List<LeagueDomain>> = ResultWrapper.Success(leagues)
            result
        }

    }

    override suspend fun localSave(dataList: List<LeagueDomain>) {
        iLeagueLocalRepository.saveAll(dataList)
    }


}