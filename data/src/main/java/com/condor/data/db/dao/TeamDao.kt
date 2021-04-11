package com.condor.data.db.dao

import androidx.room.*
import com.condor.data.db.entity.TeamEntity
import com.condor.data.db.entity.TeamWithEvents
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(team: TeamEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(teams: List<TeamEntity>)

    @Update
    suspend fun update(team: TeamEntity)

    @Query("SELECT * FROM team")
    fun getAll(): Flow<List<TeamEntity>>

    @Query("SELECT * FROM team WHERE id_team = :id")
    fun getById(id: String): Flow<List<TeamEntity>>

    @Delete
    suspend fun delete(data: TeamEntity)

    @Transaction
    @Query("SELECT * FROM team")
    suspend fun getTeamWithEvents(): List<TeamWithEvents>
}