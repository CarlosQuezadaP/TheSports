package com.condor.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.condor.data.db.entity.EventEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao : BaseDao<EventEntity> {

    @Query("SELECT * FROM event")
    fun getAll(): Flow<List<EventEntity>>

    @Query("SELECT * FROM event WHERE id_team = :id")
    fun getById(id: String): Flow<List<EventEntity>>

}