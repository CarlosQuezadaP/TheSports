package com.condor.thesports.adapter

import androidx.recyclerview.widget.RecyclerView
import com.condor.domain.models.LeagueDomain
import com.condor.thesports.databinding.LeaguesItemLayoutBinding
import com.condor.thesports.handlers.ISelectLeague

class LeaguesViewHolder(
    private val itemViewBinding: LeaguesItemLayoutBinding,
    private val iSelectLeague: ISelectLeague
) :
    RecyclerView.ViewHolder(itemViewBinding.root) {

    fun binto(leagueDomain: LeagueDomain) {
        itemViewBinding.league = leagueDomain
        itemViewBinding.selectLeague = iSelectLeague
    }

}