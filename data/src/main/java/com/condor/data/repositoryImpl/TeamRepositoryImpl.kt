package com.condor.data.repositoryImpl

import com.condor.data.converters.IConverter
import com.condor.data.network.SportApi
import com.condor.data.repository.RemoteRepository
import com.condor.domain.models.TeamDomain

class TeamRepositoryImpl(private val sportService: SportApi, private val iConverter: IConverter) :
    RemoteRepository<TeamDomain> {
    override suspend fun retrieveAll(leagueParameter: String): List<TeamDomain> {
        return sportService.retrieveAllTeams(leagueParameter).teams.map {
            iConverter.convertTeamDtoToDomain(it)
        }
    }

    override suspend fun retrieveById(id: String): List<TeamDomain> {
        return sportService.retrieveTeam(id).teams.map {
            iConverter.convertTeamDtoToDomain(it)
        }
    }
}