package com.condor.data.handler

import com.condor.data.repository.ILocalRepository
import com.condor.data.repository.IRemoteRepository
import com.condor.domain.models.TeamDomain

class TeamRepositoryHandler(
    iLocalRepository: ILocalRepository<TeamDomain>,
    iRemoteRepository: IRemoteRepository<TeamDomain>
) : RepositoryHandler<TeamDomain>(iLocalRepository, iRemoteRepository) {

    override suspend fun localSave(dataList: List<TeamDomain>, id: String) {
        dataList.forEach { team: TeamDomain ->
            iLocalRepository.save(team)
        }
    }

}