package com.condor.thesports.adapter

import androidx.recyclerview.widget.RecyclerView
import com.condor.domain.models.TeamDomain
import com.condor.thesports.databinding.ItemLayoutTeamsBinding

class TeamViewHolder(private val itemViewBinding: ItemLayoutTeamsBinding) :
    RecyclerView.ViewHolder(itemViewBinding.root) {

    fun binto(teamitemDomain: TeamDomain) {
        itemViewBinding.team = teamitemDomain
    }

}