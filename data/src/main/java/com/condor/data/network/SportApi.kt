package com.condor.data.network


import com.condor.data.response.LeagueResponse
import com.condor.data.response.ResponseEvent
import com.condor.data.response.TeamResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SportApi{

    @GET("all_leagues.php")
    suspend fun retrieveAllTeams(): LeagueResponse

    @GET("search_all_teams.php")
    suspend fun retrieveAllTeams(@Query("l") leagueParameter: String): TeamResponse

    @GET("lookupteam.php")
    suspend fun retrieveTeam(@Query("id") teamId: String): TeamResponse

    @GET("eventsnext.php")
    suspend fun retrieveAllEventsByTeamId(@Query("id") teamId: String): ResponseEvent
}