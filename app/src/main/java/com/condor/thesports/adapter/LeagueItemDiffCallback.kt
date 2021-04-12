package com.condor.thesports.adapter

import androidx.recyclerview.widget.DiffUtil
import com.condor.domain.models.LeagueDomain

class LeagueItemDiffCallback : DiffUtil.ItemCallback<LeagueDomain>() {

    override fun areItemsTheSame(oldItem: LeagueDomain, newItem: LeagueDomain): Boolean {
        return oldItem.idLeague.equals(newItem.idLeague)
    }

    override fun areContentsTheSame(oldItem: LeagueDomain, newItem: LeagueDomain): Boolean {
        return oldItem == newItem
    }
}