package com.condor.data.handler

import com.condor.domain.models.TeamDomain

class BuilderTeam {

    fun buildTeam() = TeamDomain(
        "1",
        "Real Madrid"
    )

    fun buildAsList() = listOf(buildTeam())

}