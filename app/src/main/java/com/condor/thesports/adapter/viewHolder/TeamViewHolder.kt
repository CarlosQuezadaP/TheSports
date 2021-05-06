package com.condor.thesports.adapter.viewHolder

import com.condor.domain.models.TeamDomain
import com.condor.thesports.adapter.viewHolder.base.BaseViewHolder
import com.condor.thesports.databinding.ItemLayoutTeamsBinding
import com.condor.thesports.handlers.OnClick

class TeamViewHolder(
    private val itemViewBinding: ItemLayoutTeamsBinding,
    private val OnClick: OnClick<TeamDomain>
) :
    BaseViewHolder<TeamDomain>(itemViewBinding) {


    override fun bin(data: TeamDomain) {
        itemViewBinding.team = data
        itemViewBinding.onClick = OnClick
    }

}

