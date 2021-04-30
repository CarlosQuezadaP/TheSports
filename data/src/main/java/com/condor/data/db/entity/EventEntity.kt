package com.condor.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event")
data class EventEntity(
    @PrimaryKey
    val id: String,
    var id_team: String,
    var str_event: String
)