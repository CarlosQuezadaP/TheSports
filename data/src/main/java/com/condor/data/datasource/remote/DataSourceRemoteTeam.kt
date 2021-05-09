package com.condor.data.datasource.remote

import com.condor.data.converters.IConverter
import com.condor.data.network.SportApi
import com.condor.domain.models.EventDomain
import com.condor.domain.models.TeamDomain

class DataSourceRemoteTeam(
    private val sportService: SportApi,
    private val iConverter: IConverter
) : IDataSourceRemoteTeam {

    override suspend fun getAll(leagueParameter: String): List<TeamDomain> {

        val response = sportService.getAllTeams(leagueParameter)

        val teamsDto = response.teams ?: throw Exception("No teams for this category")

        return teamsDto.map {
            iConverter.convertTeamDtoToDomain(it)
        }
    }

    override suspend fun getById(id: String): List<TeamDomain> {

        val response = sportService.getTeam(id)

        val teamsDto = response.teams ?: throw Exception("No teams for this category")

        return teamsDto.map {
            iConverter.convertTeamDtoToDomain(it)
        }
    }

    override suspend fun getEventsById(id: String): List<EventDomain> {

        val response = sportService.getAllEventsByTeamId(id)

        val eventsDto = response.results ?: throw Exception("No Events to show")

        return eventsDto.map {
            iConverter.convertEventDtoToDomain(it)
        }
    }

}