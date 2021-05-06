package com.condor.thesports.adapter

import com.condor.domain.models.TeamDomain
import com.condor.thesports.R
import com.condor.thesports.adapter.base.BaseAdapterEvent
import com.condor.thesports.adapter.viewHolder.TeamViewHolder
import com.condor.thesports.databinding.ItemLayoutTeamsBinding
import com.condor.thesports.handlers.OnClick

class TeamsAdapter(iOnClick: OnClick<TeamDomain>?) :
    BaseAdapterEvent<TeamDomain, TeamViewHolder, ItemLayoutTeamsBinding>(iOnClick) {

    override val layoutId: Int
        get() = R.layout.item_layout_teams

    override fun getViewHolder(
        databinding: ItemLayoutTeamsBinding,
        iOnClick: OnClick<TeamDomain>?
    ): TeamViewHolder {
        return TeamViewHolder(databinding, iOnClick!!)
    }
}
