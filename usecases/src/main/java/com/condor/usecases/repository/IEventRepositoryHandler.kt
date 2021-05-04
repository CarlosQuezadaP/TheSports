package com.condor.usecases.repository

import com.condor.core.ResultWrapper
import com.condor.domain.models.EventDomain
import kotlinx.coroutines.flow.Flow

interface IEventRepositoryHandler {
    fun getById(id: String): Flow<ResultWrapper<List<EventDomain>>>
}
