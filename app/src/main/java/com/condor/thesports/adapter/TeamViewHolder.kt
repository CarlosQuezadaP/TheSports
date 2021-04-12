package com.condor.thesports.adapter

import androidx.recyclerview.widget.RecyclerView
import com.condor.domain.models.TeamDomain
import com.condor.thesports.databinding.ItemLayoutTeamsBinding
import com.condor.thesports.handlers.OnTeamClick

class TeamViewHolder(
    private val itemViewBinding: ItemLayoutTeamsBinding,
    private val onTeamClick: OnTeamClick
) :
    RecyclerView.ViewHolder(itemViewBinding.root) {

    fun binto(teamitemDomain: TeamDomain) {
        itemViewBinding.team = teamitemDomain
        itemViewBinding.clickTeam = onTeamClick

    }

}