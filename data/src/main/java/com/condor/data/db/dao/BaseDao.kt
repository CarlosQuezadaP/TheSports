package com.condor.data.db.dao

import androidx.room.*

@Dao
interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(entity: List<T>)

    @Update
    suspend fun update(entity: T)

    @Delete
    suspend fun delete(data: T)
}