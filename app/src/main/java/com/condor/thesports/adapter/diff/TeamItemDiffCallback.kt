package com.condor.thesports.adapter.diff

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.condor.domain.models.TeamDomain

class TeamItemDiffCallback : DiffUtil.ItemCallback<TeamDomain>() {

    override fun areItemsTheSame(oldItem: TeamDomain, newItem: TeamDomain): Boolean {
        return oldItem.idTeam.equals(newItem.idTeam)
    }

    override fun areContentsTheSame(oldItem: TeamDomain, newItem: TeamDomain): Boolean {
        return oldItem == newItem
    }
}