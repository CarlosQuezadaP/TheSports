package com.condor.thesports.adapter.viewHolder

import com.condor.domain.models.LeagueDomain
import com.condor.thesports.adapter.viewHolder.base.BaseViewHolder
import com.condor.thesports.databinding.LeaguesItemLayoutBinding
import com.condor.thesports.handlers.OnClick

class LeaguesViewHolder(
    private val viewDataBinding: LeaguesItemLayoutBinding,
    private val OnClick: OnClick<LeagueDomain>
) :
    BaseViewHolder<LeagueDomain>(viewDataBinding) {


    override fun bin(data: LeagueDomain) {
        viewDataBinding.league = data
        viewDataBinding.onClick = OnClick
    }

}
