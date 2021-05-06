package com.condor.thesports.handlers

import com.condor.domain.models.TeamDomain

interface OnTeamClick {
    fun onClick(team: TeamDomain)
}