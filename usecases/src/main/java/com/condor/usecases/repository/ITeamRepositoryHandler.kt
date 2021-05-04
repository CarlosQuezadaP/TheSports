package com.condor.usecases.repository

import com.condor.core.ResultWrapper
import com.condor.domain.models.TeamDomain
import kotlinx.coroutines.flow.Flow

interface ITeamRepositoryHandler {
    fun getAll(leagueParameter: String): Flow<ResultWrapper<List<TeamDomain>>>
    fun getById(id: String): Flow<ResultWrapper<List<TeamDomain>>>
}
