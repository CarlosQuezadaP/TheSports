package com.condor.data.base

import com.condor.data.datasource.local.ILocalRepository

abstract class BaseRepositoryHandler<T>(
    val iLocalRepository: ILocalRepository<T>,
) {
    abstract suspend fun localSave(dataList: List<T>)
}