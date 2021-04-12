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

class Converter : IConverter {
    override fun convertEventDtoToDomain(eventDto: EventDto): EventDomain {
        return EventDomain(eventDto.idEvent, eventDto.idHomeTeam, eventDto.strEvent)
    }

    override fun convertEventDomainToEntity(eventDomain: EventDomain): EventEntity {
        return EventEntity(
            id = eventDomain.idEvent,
            id_team = eventDomain.idTeam,
            str_event = eventDomain.strEvent
        )
    }

    override fun convertEventEntityToDomain(eventEntity: EventEntity): EventDomain {
        return EventDomain(eventEntity.id, eventEntity.id_team, eventEntity.str_event)
    }

    override fun convertLeagueDtoToDomain(leagueDto: LeagueDto): LeagueDomain {
        return LeagueDomain(leagueDto.idLeague, leagueDto.strLeague)
    }

    override fun convertLeagueDomainToEntity(leagueDomain: LeagueDomain): LeagueEntity {
        return LeagueEntity(leagueDomain.idLeague, leagueDomain.strLeague)

    }

    override fun convertLeagueEntityToDomain(leagueEntity: LeagueEntity): LeagueDomain {
        return LeagueDomain(leagueEntity.id, leagueEntity.league_name)

    }

    override fun convertTeamDtoToDomain(teamDto: TeamDto): TeamDomain {
        return TeamDomain(
            teamDto.idTeam,
            teamDto.strTeam,
            teamDto.intFormedYear,
            teamDto.strStadium,
            teamDto.strWebsite,
            teamDto.strFacebook,
            teamDto.strTwitter,
            teamDto.strInstagram,
            teamDto.strDescriptionEN,
            teamDto.strTeamBadge,
            teamDto.strTeamJersey,
            teamDto.strYoutube
        )
    }

    override fun convertTeamDomainToEntity(teamDomain: TeamDomain): TeamEntity {
        return TeamEntity(
            teamDomain.idTeam,
            teamDomain.strTeam,
            teamDomain.intFormedYear,
            teamDomain.strStadium,
            teamDomain.strWebsite,
            teamDomain.strFacebook,
            teamDomain.strTwitter,
            teamDomain.strInstagram,
            teamDomain.strDescriptionEN,
            teamDomain.strTeamBadge,
            teamDomain.strTeamJersey,
            teamDomain.strYoutube
        )
    }

    override fun convertTeamEntityToDomain(teamEntity: TeamEntity): TeamDomain {
        return TeamDomain(
            teamEntity.id_team,
            teamEntity.str_team,
            teamEntity.int_formedYear,
            teamEntity.strS_stadium,
            teamEntity.str_website,
            teamEntity.str_facebook,
            teamEntity.str_twitter,
            teamEntity.str_instagram,
            teamEntity.str_descriptionEN,
            teamEntity.str_teamBadge,
            teamEntity.st_teamJersey,
            teamEntity.str_youtube
        )
    }


}