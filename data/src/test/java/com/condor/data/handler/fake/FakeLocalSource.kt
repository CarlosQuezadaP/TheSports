package com.condor.data.handler.fake

import com.condor.data.datasource.local.ILocalRepository
import com.condor.domain.models.TeamDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


class FakeLocalSource : ILocalRepository<TeamDomain> {

    val teams = mutableListOf<TeamDomain>()

    override suspend fun save(data: TeamDomain) {
        this.teams += data
    }

    override suspend fun saveAll(data: List<TeamDomain>) {
        this.teams += data
    }

    override fun getAll(leagueParameter: String): Flow<List<TeamDomain>> {
        return flowOf(teams)
    }

    override fun getById(id: String): Flow<List<TeamDomain>> {
        return flowOf(teams)
    }

    override suspend fun delete(data: TeamDomain) {
        teams.remove(data)
    }

}