package com.condor.data.datasource.remote

import com.condor.data.converters.IConverter
import com.condor.data.network.SportApi
import com.condor.data.repository.IRemoteRepository
import com.condor.domain.models.TeamDomain

class DataSourceRemoteTeam(private val sportService: SportApi, private val iConverter: IConverter) :
    IRemoteRepository<TeamDomain> {
    override suspend fun getAll(leagueParameter: String): List<TeamDomain> {
        return sportService.getAllTeams(leagueParameter).teams.map {
            iConverter.convertTeamDtoToDomain(it)
        }
    }

    override suspend fun getById(id: String): List<TeamDomain> {
        return sportService.getTeam(id).teams.map {
            iConverter.convertTeamDtoToDomain(it)
        }
    }
}