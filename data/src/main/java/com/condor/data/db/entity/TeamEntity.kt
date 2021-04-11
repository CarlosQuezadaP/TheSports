package com.condor.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "team")
data class TeamEntity(
    @PrimaryKey
    val id_team: String = "",
    val str_team: String = "",
    val int_formedYear: String = "",
    val strS_stadium: String = "",
    val str_website: String = "",
    val str_facebook: String = "",
    val str_twitter: String = "",
    val str_instagram: String = "",
    val str_descriptionEN: String = "",
    val str_teamBadge: String = "",
    val st_teamJersey: String = "",
    val str_youtube: String = ""
)