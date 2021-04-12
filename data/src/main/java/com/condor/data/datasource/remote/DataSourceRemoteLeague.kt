package com.condor.data.datasource.remote

import com.condor.data.converters.IConverter
import com.condor.data.network.SportApi
import com.condor.data.repository.ILeagueRemoteRepository
import com.condor.domain.models.LeagueDomain

class DataSourceRemoteLeague(val sportApi: SportApi, private val iConverter: IConverter) :
    ILeagueRemoteRepository {
    override suspend fun getAllLeagues(): List<LeagueDomain> {
        return sportApi.getAllLeagues().leagueDtos.map {
            iConverter.convertLeagueDtoToDomain(it)
        }
    }
}