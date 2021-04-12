package com.condor.thesports.adapter

import androidx.recyclerview.widget.RecyclerView
import com.condor.domain.models.EventDomain
import com.condor.thesports.databinding.ItemEventBinding

class EventViewHolder(
    private val itemViewBinding: ItemEventBinding,
) :
    RecyclerView.ViewHolder(itemViewBinding.root) {

    fun binto(eventDomain: EventDomain) {
        itemViewBinding.textViewNameEvent.text = eventDomain.strEvent
    }

}