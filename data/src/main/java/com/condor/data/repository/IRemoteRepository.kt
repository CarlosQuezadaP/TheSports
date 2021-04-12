package com.condor.data.repository

interface IRemoteRepository<T> {
    suspend fun getAll(leagueParameter: String): List<T>
    suspend fun getById(id: String): List<T>
}
