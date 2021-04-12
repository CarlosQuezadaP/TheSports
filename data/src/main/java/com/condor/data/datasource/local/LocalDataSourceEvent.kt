package com.condor.data.datasource.local


import com.condor.data.converters.IConverter
import com.condor.data.db.dao.EventDao
import com.condor.data.db.entity.EventEntity
import com.condor.data.repository.LocalRepository
import com.condor.domain.models.EventDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalDataSourceEvent(private val eventDao: EventDao, private val iConverter: IConverter) :
    LocalRepository<EventDomain> {
    override suspend fun save(data: EventDomain) {
        val eventEntity: EventEntity = iConverter.convertEventDomainToEntity(data)
        eventDao.save(eventEntity)
    }


    override fun getAll(leagueParameter: String): Flow<List<EventDomain>> {
        return eventDao.getAll().map {
            it.map {
                iConverter.convertEventEntityToDomain(it)
            }
        }
    }

    override fun getById(id: String): Flow<List<EventDomain>> {
        return eventDao.getById(id).map {
            it.map {
                iConverter.convertEventEntityToDomain(it)
            }
        }
    }

    override suspend fun delete(data: EventDomain) {
        val eventEntity: EventEntity = iConverter.convertEventDomainToEntity(data)
        eventDao.delete(eventEntity)
    }

    override suspend fun saveAll(data: List<EventDomain>) {
    }

}