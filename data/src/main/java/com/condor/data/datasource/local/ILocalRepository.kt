package com.condor.data.datasource.local

import kotlinx.coroutines.flow.Flow

interface ILocalRepository<T> {

    suspend fun save(data: T)

    suspend fun saveAll(data: List<T>)

    fun getAll(leagueParameter: String): Flow<List<T>>

    fun getById(id: String): Flow<List<T>>

    suspend fun delete(data: T)
}