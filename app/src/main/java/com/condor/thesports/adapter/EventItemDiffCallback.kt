package com.condor.thesports.adapter

import androidx.recyclerview.widget.DiffUtil
import com.condor.domain.models.EventDomain

class EventItemDiffCallback : DiffUtil.ItemCallback<EventDomain>() {

    override fun areItemsTheSame(oldItem: EventDomain, newItem: EventDomain): Boolean {
        return oldItem.idEvent.equals(newItem.idEvent)
    }

    override fun areContentsTheSame(oldItem: EventDomain, newItem: EventDomain): Boolean {
        return oldItem == newItem
    }
}