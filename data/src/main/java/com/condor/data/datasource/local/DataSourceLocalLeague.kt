package com.condor.data.datasource.local

import com.condor.data.converters.IConverter
import com.condor.data.db.dao.LeagueDao
import com.condor.domain.models.LeagueDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataSourceLocalLeague(val leagueDao: LeagueDao, private val iConverter: IConverter) :
    ILocalRepository<LeagueDomain>, IDataSourceLocalLeague {

    override suspend fun saveAll(data: List<LeagueDomain>) {
        val leaguesEntities = data.map {
            iConverter.convertLeagueDomainToEntity(it)
        }
        leagueDao.saveAll(leaguesEntities)
    }

    override fun getLeagues(): Flow<List<LeagueDomain>> {
        return leagueDao.getLeagues().map {
            val leagues: List<LeagueDomain> = it.map {
                iConverter.convertLeagueEntityToDomain(it)
            }
            leagues
        }
    }

    override suspend fun save(data: LeagueDomain) {
        leagueDao.save(iConverter.convertLeagueDomainToEntity(data))
    }

    override fun getAll(leagueParameter: String): Flow<List<LeagueDomain>> {
        return leagueDao.getLeagues().map {
            it.map {
                iConverter.convertLeagueEntityToDomain(it)
            }
        }
    }

    override fun getById(id: String): Flow<List<LeagueDomain>> {
        return leagueDao.getLeagues().map {
            it.map {
                iConverter.convertLeagueEntityToDomain(it)
            }
        }
    }

    override suspend fun delete(data: LeagueDomain) {
        leagueDao.delete(iConverter.convertLeagueDomainToEntity(data))
    }
}