package com.condor.data.handler

import com.condor.data.repository.LocalRepository
import com.condor.data.repository.RemoteRepository
import com.condor.domain.models.TeamDomain

class TeamRepositoryHandler(
    localRepository: LocalRepository<TeamDomain>,
    remoteRepository: RemoteRepository<TeamDomain>
) : RepositoryHandler<TeamDomain>(localRepository, remoteRepository) {

    override suspend fun localSave(dataList: List<TeamDomain>, id: String) {
        dataList.forEach { team: TeamDomain ->
            localRepository.save(team)
        }
    }

}