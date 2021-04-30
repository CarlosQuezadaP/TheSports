package com.condor.data.converters

import com.condor.data.db.entity.EventEntity
import com.condor.data.db.entity.LeagueEntity
import com.condor.data.db.entity.TeamEntity
import com.condor.data.dto.EventDto
import com.condor.data.dto.LeagueDto
import com.condor.data.dto.TeamDto
import com.condor.domain.models.EventDomain
import com.condor.domain.models.LeagueDomain
import com.condor.domain.models.TeamDomain

interface IConverter {
    fun convertEventDtoToDomain(eventDto: EventDto): EventDomain
    fun convertEventDomainToEntity(eventDomain: EventDomain): EventEntity
    fun convertEventEntityToDomain(eventEntity: EventEntity): EventDomain
    fun convertLeagueDtoToDomain(leagueDto: LeagueDto): LeagueDomain
    fun convertLeagueDomainToEntity(leagueDomain: LeagueDomain): LeagueEntity
    fun convertLeagueEntityToDomain(leagueEntity: LeagueEntity): LeagueDomain
    fun convertTeamDtoToDomain(teamDto: TeamDto): TeamDomain
    fun convertTeamDomainToEntity(teamDomain: TeamDomain): TeamEntity
    fun convertTeamEntityToDomain(teamEntity: TeamEntity): TeamDomain
}