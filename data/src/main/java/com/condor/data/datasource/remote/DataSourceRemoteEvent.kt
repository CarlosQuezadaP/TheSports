package com.condor.data.datasource.remote

import com.condor.data.converters.IConverter
import com.condor.data.network.SportApi
import com.condor.domain.models.EventDomain

class DataSourceRemoteEvent(
    private val sportService: SportApi,
    private val iConverter: IConverter
) :
    IDataSourceRemoteEvent {
    override suspend fun getById(id: String): List<EventDomain> {
        return sportService.getAllEventsByTeamId(id).results.map {
            iConverter.convertEventDtoToDomain(it)
        }
    }
}