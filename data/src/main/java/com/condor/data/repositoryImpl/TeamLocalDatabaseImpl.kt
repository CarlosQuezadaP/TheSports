package com.condor.data.repositoryImpl

import com.condor.data.converters.IConverter
import com.condor.data.db.dao.TeamDao
import com.condor.data.db.entity.TeamEntity
import com.condor.data.repository.LocalRepository
import com.condor.domain.models.TeamDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class TeamLocalDatabaseImpl(private val teamDao: TeamDao, private val iConverter: IConverter) :
    LocalRepository<TeamDomain> {
    override suspend fun save(data: TeamDomain) {
        val teamEntity: TeamEntity = iConverter.convertTeamDomainToEntity(data)
        teamDao.save(teamEntity)
    }

    override suspend fun saveAll(data: List<TeamDomain>) {
        val teamsEntity: List<TeamEntity> = data.map {
            iConverter.convertTeamDomainToEntity(it)
        }
        teamDao.saveAll(teamsEntity)
    }


    override fun getAll(leagueParameter: String): Flow<List<TeamDomain>> {
        return teamDao.getAll().map {
            it.map {
                iConverter.convertTeamEntityToDomain(it)
            }
        }
    }

    override fun getById(id: String): Flow<List<TeamDomain>> {
        return teamDao.getById(id).map {
            it.map {
                iConverter.convertTeamEntityToDomain(it)
            }
        }
    }

    override suspend fun delete(data: TeamDomain) {
    }

}