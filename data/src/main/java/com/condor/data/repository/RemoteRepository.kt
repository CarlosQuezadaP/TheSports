package com.condor.data.repository

interface RemoteRepository<T> {
    suspend fun getAll(leagueParameter: String): List<T>
    suspend fun getById(id: String): List<T>
}
