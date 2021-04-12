package com.condor.data.datasource.local


import com.condor.data.converters.IConverter
import com.condor.data.db.dao.LeagueDao
import com.condor.data.repository.ILeagueLocalRepository
import com.condor.domain.models.LeagueDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataSourceLocalLeague(val leagueDao: LeagueDao, private val iConverter: IConverter) :
    ILeagueLocalRepository {

    override fun saveLeagues(leagues: List<LeagueDomain>) {
        val leaguesEntities = leagues.map {
            iConverter.convertLeagueDomainToEntity(it)
        }
        leagueDao.saveAllLeagues(leaguesEntities)
    }

    override fun getLeagues(): Flow<List<LeagueDomain>> {
        return leagueDao.getLeagues().map {
            val leagues: List<LeagueDomain> = it.map {
                iConverter.convertLeagueEntityToDomain(it)
            }
            leagues
        }
    }
}