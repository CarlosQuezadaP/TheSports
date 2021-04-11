package com.condor.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "league")
data class LeagueEntity (
    @PrimaryKey var id: String,
    var league_name: String
)