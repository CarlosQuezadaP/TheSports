package com.condor.data.repository

import com.condor.domain.models.LeagueDomain
import kotlinx.coroutines.flow.Flow

interface LocalLeagueRepository {
    fun saveLeagues(leagues: List<LeagueDomain>)
    fun getLeagues(): Flow<List<LeagueDomain>>
}