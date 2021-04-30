package com.condor.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.condor.data.db.dao.EventDao
import com.condor.data.db.dao.LeagueDao
import com.condor.data.db.dao.TeamDao
import com.condor.data.db.entity.EventEntity
import com.condor.data.db.entity.LeagueEntity
import com.condor.data.db.entity.TeamEntity

@Database(
    entities = [TeamEntity::class, EventEntity::class, LeagueEntity::class],
    version = 2,
    exportSchema = false
)
abstract class TheSportsDB : RoomDatabase() {
    abstract fun teamDao(): TeamDao
    abstract fun eventDao(): EventDao
    abstract fun leagueDao(): LeagueDao
}