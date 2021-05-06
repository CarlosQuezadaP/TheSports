package com.condor.thesports.adapter

import com.condor.domain.models.LeagueDomain
import com.condor.thesports.R
import com.condor.thesports.adapter.base.BaseAdapterEvent
import com.condor.thesports.adapter.viewHolder.LeaguesViewHolder
import com.condor.thesports.databinding.LeaguesItemLayoutBinding
import com.condor.thesports.handlers.OnClick

class LeagueListAdapter(iOnClick: OnClick<LeagueDomain>?) :
    BaseAdapterEvent<LeagueDomain, LeaguesViewHolder, LeaguesItemLayoutBinding>(iOnClick) {

    override val layoutId: Int
        get() = R.layout.leagues_item_layout

    override fun getViewHolder(
        databinding: LeaguesItemLayoutBinding,
        iOnClick: OnClick<LeagueDomain>?
    ): LeaguesViewHolder {
        return LeaguesViewHolder(databinding, iOnClick!!)
    }
}