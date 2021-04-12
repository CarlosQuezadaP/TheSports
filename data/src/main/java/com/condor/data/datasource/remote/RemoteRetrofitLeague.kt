package com.condor.data.datasource.remote

import com.condor.data.converters.IConverter
import com.condor.data.network.SportApi
import com.condor.data.repository.LeagueRemoteRepository
import com.condor.domain.models.LeagueDomain

class RemoteRetrofitLeague(val sportApi: SportApi, private val iConverter: IConverter) :
    LeagueRemoteRepository {
    override suspend fun retrieveAllLeagues(): List<LeagueDomain> {
        return sportApi.retrieveAllTeams().leagueDtos.map {
            iConverter.convertLeagueDtoToDomain(it)
        }
    }
}