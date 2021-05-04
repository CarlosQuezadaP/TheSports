package com.condor.data.handler

import com.condor.core.ResultWrapper
import com.condor.data.base.BaseRepositoryHandler
import com.condor.data.datasource.local.ILocalRepository
import com.condor.data.datasource.remote.IDataSourceRemoteTeam
import com.condor.domain.models.TeamDomain
import com.condor.usecases.repository.ITeamRepositoryHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class TeamRepositoryHandler(
    iLocalRepository: ILocalRepository<TeamDomain>,
    private val iDataSourceRemoteTeam: IDataSourceRemoteTeam
) : BaseRepositoryHandler<TeamDomain>(iLocalRepository), ITeamRepositoryHandler {


    override fun getAll(leagueParameter: String): Flow<ResultWrapper<List<TeamDomain>>> {
        return getAllToFlow(leagueParameter).map {
            val response: ResultWrapper<List<TeamDomain>> = ResultWrapper.Success(it)
            response
        }
    }

    private fun getAllToFlow(leagueParameter: String): Flow<List<TeamDomain>> {
        return flow {
            val response: List<TeamDomain> = iDataSourceRemoteTeam.getAll(leagueParameter)
            localSave(response)
            emit(response)
        }
    }

    override fun getById(id: String): Flow<ResultWrapper<List<TeamDomain>>> {
        return iLocalRepository.getById(id).flatMapConcat { localData: List<TeamDomain> ->
            if (localData.isNotEmpty())
                flowOf(localData)
            else {
                getByIdToFlow(id)
            }
        }.map {
            val response: ResultWrapper<List<TeamDomain>> = ResultWrapper.Success(it)
            response
        }.onStart {
            emit(ResultWrapper.Loading)
        }.catch {
            emit(ResultWrapper.Error("Network error"))
        }.flowOn(Dispatchers.IO)
    }

    private fun getByIdToFlow(id: String): Flow<List<TeamDomain>> {
        return flow {
            val response: List<TeamDomain> = iDataSourceRemoteTeam.getById(id)
            localSave(response)
            emit(response)
        }
    }


    override suspend fun localSave(dataList: List<TeamDomain>) {
        dataList.forEach { team: TeamDomain ->
            iLocalRepository.save(team)
        }
    }

}
