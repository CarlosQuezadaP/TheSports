package com.condor.data.handler.fake

import com.condor.data.datasource.remote.IDataSourceRemoteTeam
import com.condor.data.handler.BuilderTeam
import com.condor.domain.models.EventDomain
import com.condor.domain.models.TeamDomain

val fakeTeams = BuilderTeam().buildAsList()

class FakeRemoteSource(
    private val movies: List<TeamDomain> = emptyList(),
    private val delay: Int = 0
) :
    IDataSourceRemoteTeam {


    override suspend fun getAll(leagueParameter: String): List<TeamDomain> {
        return movies
    }

    override suspend fun getById(id: String): List<TeamDomain> {
        return movies

    }

    override suspend fun getEventsById(id: String): List<EventDomain> {
        return listOf()
    }
}