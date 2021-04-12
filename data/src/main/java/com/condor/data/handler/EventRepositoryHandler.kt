package com.condor.data.handler

import com.condor.data.repository.ILocalRepository
import com.condor.data.repository.IRemoteRepository
import com.condor.domain.models.EventDomain

class EventRepositoryHandler constructor(
    iLocalRepository: ILocalRepository<EventDomain>,
    iRemoteRepository: IRemoteRepository<EventDomain>
) : RepositoryHandler<EventDomain>(iLocalRepository, iRemoteRepository) {


    override suspend fun localSave(dataList: List<EventDomain>, id: String) {
        dataList.forEach { event: EventDomain ->
            iLocalRepository.save(event.apply { idTeam = id })
        }
    }
}