package com.condor.thesports.handlers

import android.view.View
import com.condor.domain.models.TeamDomain

interface OnTeamClick {
    fun onClick(team: TeamDomain)
}