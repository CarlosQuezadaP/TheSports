package com.condor.data.handler

import com.condor.core.ResultWrapper
import com.condor.data.base.BaseRepositoryHandler
import com.condor.data.datasource.local.ILocalRepository
import com.condor.data.datasource.remote.IDataSourceRemoteTeam
import com.condor.domain.models.TeamDomain
import com.condor.usecases.repository.ITeamRepositoryHandler
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class TeamRepositoryHandler(
    iLocalRepository: ILocalRepository<TeamDomain>,
    private val iDataSourceRemoteTeam: IDataSourceRemoteTeam
) : BaseRepositoryHandler<TeamDomain>(iLocalRepository), ITeamRepositoryHandler {


    override fun getAll(leagueParameter: String) = getAllToFlow(leagueParameter).map {
        val response: ResultWrapper<List<TeamDomain>> = ResultWrapper.Success(it)
        response
    }

    override fun getById(id: String) =
        iLocalRepository.getById(id).flatMapConcat { localData: List<TeamDomain> ->
            if (localData.isNotEmpty()) {
                flowOf(localData.get(0))
            } else {
                getByIdToFlow(id)
            }
        }.map {
            val response: ResultWrapper<TeamDomain> = ResultWrapper.Success(it)
            response
        }

    private fun getAllToFlow(leagueParameter: String) = flow {
        val response: List<TeamDomain> = iDataSourceRemoteTeam.getAll(leagueParameter)
        localSave(response)
        emit(response)
    }

    private fun getByIdToFlow(id: String) = flow {
        val response: List<TeamDomain> = iDataSourceRemoteTeam.getById(id)
        localSave(response)
        emit(response.get(0))
    }

    override suspend fun localSave(dataList: List<TeamDomain>) {
        dataList.forEach { team: TeamDomain ->
            iLocalRepository.save(team)
        }
    }

}
