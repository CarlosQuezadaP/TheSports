package com.condor.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.condor.data.db.entity.TeamEntity
import com.condor.data.db.entity.TeamWithEvents
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamDao : BaseDao<TeamEntity> {

    @Query("SELECT * FROM team")
    fun getAll(): Flow<List<TeamEntity>>

    @Query("SELECT * FROM team WHERE id_team = :id")
    fun getById(id: String): Flow<List<TeamEntity>>

}