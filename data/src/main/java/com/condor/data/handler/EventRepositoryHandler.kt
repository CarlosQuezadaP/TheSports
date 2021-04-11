package com.condor.data.handler

import com.condor.data.dto.EventDto
import com.condor.data.repository.LocalRepository
import com.condor.data.repository.RemoteRepository

class EventRepositoryHandler  constructor(
    localRepository: LocalRepository<EventDto>,
    remoteRepository: RemoteRepository<EventDto>
) : RepositoryHandler<EventDto>(localRepository, remoteRepository) {


    override suspend fun localSave(dataList: List<EventDto>, id: String) {
        dataList.forEach {event: EventDto ->
            localRepository.save(event.apply { idTeam = id })
        }
    }
}