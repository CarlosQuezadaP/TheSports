package com.condor.data.repositoryImpl

import com.condor.data.converters.IConverter
import com.condor.data.network.SportApi
import com.condor.data.repository.RemoteRepository
import com.condor.domain.models.EventDomain


class EventRepositoryImpl(private val sportService: SportApi, private val iConverter: IConverter) :
    RemoteRepository<EventDomain> {

    override suspend fun retrieveAll(leagueParameter: String): List<EventDomain> {
        return emptyList()
    }

    override suspend fun retrieveById(id: String): List<EventDomain> {
        return sportService.retrieveAllEventsByTeamId(id).events.map {
            iConverter.convertEventDtoToDomain(it)
        }
    }

}