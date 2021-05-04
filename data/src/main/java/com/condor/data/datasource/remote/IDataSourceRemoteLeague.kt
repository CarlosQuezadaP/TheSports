package com.condor.data.datasource.remote

import com.condor.domain.models.LeagueDomain

interface IDataSourceRemoteLeague {
    suspend fun getAllLeagues(): List<LeagueDomain>
}