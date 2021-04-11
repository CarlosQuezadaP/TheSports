package com.condor.data.db.dao

import androidx.room.*
import com.condor.data.db.entity.EventEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(event: EventEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(events: List<EventEntity>)

    @Update
    suspend fun update(event: EventEntity)

    @Query("SELECT * FROM event")
    fun getAll(): Flow<List<EventEntity>>

    @Query("SELECT * FROM event WHERE id_team = :id")
    fun getById(id: String): Flow<List<EventEntity>>

    @Delete
    suspend fun delete(data: EventEntity)
}