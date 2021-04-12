package com.condor.data.handler

import com.condor.core.ResultWrapper
import com.condor.data.repository.LocalRepository
import com.condor.data.repository.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

abstract class RepositoryHandler<T>(
    val localRepository: LocalRepository<T>,
    val remoteRepository: RemoteRepository<T>
) {

    private fun getAllToFlow(leagueParameter: String): Flow<List<T>> {
        return flow {
            val response: List<T> = remoteRepository.getAll(leagueParameter)
            localSave(response)
            emit(response)
        }
    }

    private fun getByIdToFlow(id: String): Flow<List<T>> {
        return flow {
            val response: List<T> = remoteRepository.getById(id)

            emit(response)
        }
    }

    fun getAll(leagueParameter: String): Flow<ResultWrapper<List<T>>> {
        return getAllToFlow(leagueParameter).map {
            val response: ResultWrapper<List<T>> = ResultWrapper.Success(it)
            response
        }
    }

    fun getById(id: String): Flow<ResultWrapper<List<T>>> {
        return localRepository.getById(id).flatMapConcat { localData: List<T> ->
            if (localData.isNotEmpty())
                flowOf(localData)
            else {
                getByIdToFlow(id)
            }
        }.map {
            var response: ResultWrapper<List<T>> = ResultWrapper.Success(it)
            response
        }.onStart {
            emit(ResultWrapper.Loading)
        }.catch {
            emit(ResultWrapper.Error("Network error"))
        }.flowOn(Dispatchers.IO)
    }

    abstract suspend fun localSave(dataList: List<T>, id: String = "")

}