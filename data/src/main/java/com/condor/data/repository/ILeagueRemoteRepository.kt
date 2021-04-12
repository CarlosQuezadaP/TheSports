package com.condor.data.repository

import com.condor.domain.models.LeagueDomain

interface ILeagueRemoteRepository {
    suspend fun getAllLeagues(): List<LeagueDomain>
}