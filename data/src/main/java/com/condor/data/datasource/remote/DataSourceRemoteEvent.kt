package com.condor.data.datasource.remote

import com.condor.data.converters.IConverter
import com.condor.data.network.SportApi
import com.condor.data.repository.IRemoteRepository
import com.condor.domain.models.EventDomain


class DataSourceRemoteEvent(private val sportService: SportApi, private val iConverter: IConverter) :
    IRemoteRepository<EventDomain> {

    override suspend fun getAll(leagueParameter: String): List<EventDomain> {
        return emptyList()
    }

    override suspend fun getById(id: String): List<EventDomain> {
        return sportService.getAllEventsByTeamId(id).results.map {
            iConverter.convertEventDtoToDomain(it)
        }
    }

}