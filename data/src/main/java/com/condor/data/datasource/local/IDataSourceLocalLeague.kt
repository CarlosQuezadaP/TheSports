package com.condor.data.datasource.local

import com.condor.domain.models.LeagueDomain
import kotlinx.coroutines.flow.Flow

interface IDataSourceLocalLeague {
    fun getLeagues(): Flow<List<LeagueDomain>>
}