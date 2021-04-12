package com.condor.usecases

import com.condor.core.ResultWrapper
import com.condor.data.handler.EventRepositoryHandler
import com.condor.domain.models.EventDomain
import kotlinx.coroutines.flow.Flow

class GetAllEventsUseCase(private val eventRepositoryHandler: EventRepositoryHandler) :
    IGetAllEventsUseCase {

    override fun invoke(id: String) =
        eventRepositoryHandler.getById(id)

}

interface IGetAllEventsUseCase {
    fun invoke(id: String): Flow<ResultWrapper<List<EventDomain>>>
}