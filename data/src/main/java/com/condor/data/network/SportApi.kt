package com.condor.data.network


import com.condor.data.response.LeagueResponse
import com.condor.data.response.ResponseEvent
import com.condor.data.response.TeamResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SportApi {

    @GET("all_leagues.php")
    suspend fun getAllLeagues(): LeagueResponse

    @GET("search_all_teams.php")
    suspend fun getAllTeams(@Query("l") leagueParameter: String): TeamResponse

    @GET("lookupteam.php")
    suspend fun getTeam(@Query("id") teamId: String): TeamResponse

    @GET("eventslast.php")
    suspend fun getAllEventsByTeamId(@Query("id") teamId: String): ResponseEvent
}

