package com.condor.data.repository

import com.condor.domain.models.LeagueDomain

interface LeagueRemoteRepository {
    suspend fun retrieveAllLeagues(): List<LeagueDomain>
}