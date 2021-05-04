package com.condor.thesports.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.condor.domain.models.EventDomain
import com.condor.thesports.R
import com.condor.thesports.adapter.diff.EventItemDiffCallback
import com.condor.thesports.adapter.viewHolder.EventViewHolder
import com.condor.thesports.databinding.ItemEventBinding

class EventListAdapter :
    ListAdapter<EventDomain, EventViewHolder>(EventItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val layout = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_event,
            parent,
            false
        ) as ItemEventBinding


        return EventViewHolder(layout)

    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.binto(getItem(position))

    }

}