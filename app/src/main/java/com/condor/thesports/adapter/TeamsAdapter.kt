package com.condor.thesports.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.condor.domain.models.TeamDomain
import com.condor.thesports.R
import com.condor.thesports.databinding.ItemLayoutTeamsBinding
import com.condor.thesports.handlers.OnTeamClick

class TeamsAdapter(private val onTeamClick: OnTeamClick) :
    ListAdapter<TeamDomain, TeamViewHolder>(TeamItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val layout = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_layout_teams,
            parent,
            false
        ) as ItemLayoutTeamsBinding

        return TeamViewHolder(layout, onTeamClick)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.binto(getItem(position))
    }

}