package com.condor.thesports.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.condor.domain.models.LeagueDomain
import com.condor.thesports.R
import com.condor.thesports.databinding.LeaguesItemLayoutBinding
import com.condor.thesports.handlers.ISelectLeague

class LeagueListAdapter(private val iSelectLeague: ISelectLeague) :
    ListAdapter<LeagueDomain, LeaguesViewHolder>(LeagueItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaguesViewHolder {
        val layout = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.leagues_item_layout,
            parent,
            false
        ) as LeaguesItemLayoutBinding


        return LeaguesViewHolder(layout, iSelectLeague)

    }

    override fun onBindViewHolder(holder: LeaguesViewHolder, position: Int) {
        holder.binto(getItem(position))

    }

}