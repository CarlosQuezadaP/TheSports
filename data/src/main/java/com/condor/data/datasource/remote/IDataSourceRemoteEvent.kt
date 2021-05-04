package com.condor.data.datasource.remote

import com.condor.domain.models.EventDomain

interface IDataSourceRemoteEvent {
    suspend fun getById(id: String): List<EventDomain>
}
