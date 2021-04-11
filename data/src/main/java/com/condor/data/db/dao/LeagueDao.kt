package com.condor.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.condor.data.db.entity.LeagueEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LeagueDao {
    @Insert
    fun saveAllLeagues(leagues: List<LeagueEntity>)

    @Query("SELECT * FROM league")
    fun getLeagues(): Flow<List<LeagueEntity>>
}