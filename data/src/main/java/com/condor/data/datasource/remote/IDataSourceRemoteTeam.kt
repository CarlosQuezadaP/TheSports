package com.condor.data.datasource.remote

import com.condor.domain.models.EventDomain
import com.condor.domain.models.TeamDomain

interface IDataSourceRemoteTeam {
    suspend fun getAll(leagueParameter: String): List<TeamDomain>
    suspend fun getById(id: String): List<TeamDomain>
    suspend fun getEventsById(id: String): List<EventDomain>
}
