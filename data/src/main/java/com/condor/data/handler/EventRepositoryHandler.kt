package com.condor.data.handler

import com.condor.core.ResultWrapper
import com.condor.data.base.BaseRepositoryHandler
import com.condor.data.datasource.local.ILocalRepository
import com.condor.data.datasource.remote.IDataSourceRemoteEvent
import com.condor.domain.models.EventDomain
import com.condor.usecases.repository.IEventRepositoryHandler
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class EventRepositoryHandler constructor(
    iLocalRepository: ILocalRepository<EventDomain>,
    private val iDataSourceRemoteEvent: IDataSourceRemoteEvent
) : BaseRepositoryHandler<EventDomain>(iLocalRepository), IEventRepositoryHandler {

    override suspend fun localSave(dataList: List<EventDomain>) {
        dataList.forEach { event: EventDomain ->
            iLocalRepository.save(event)
        }
    }

    override fun getById(id: String) =
        iLocalRepository.getById(id).flatMapConcat { localData: List<EventDomain> ->
            if (localData.isNotEmpty())
                flowOf(localData)
            else {
                getByIdToFlow(id)
            }
        }.map {
            val response: ResultWrapper<List<EventDomain>> = ResultWrapper.Success(it)
            response
        }

    private fun getByIdToFlow(id: String) = flow {
        val response: List<EventDomain> = iDataSourceRemoteEvent.getById(id)
        localSave(response)
        emit(response)
    }

}