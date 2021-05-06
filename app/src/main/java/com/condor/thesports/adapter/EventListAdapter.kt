package com.condor.thesports.adapter

import com.condor.domain.models.EventDomain
import com.condor.thesports.R
import com.condor.thesports.adapter.base.BaseAdapterEvent
import com.condor.thesports.adapter.viewHolder.EventViewHolder
import com.condor.thesports.databinding.ItemEventBinding
import com.condor.thesports.handlers.OnClick


class EventListAdapter(iOnClick: OnClick<EventDomain>?) :
    BaseAdapterEvent<EventDomain, EventViewHolder, ItemEventBinding>(iOnClick) {

    override val layoutId: Int
        get() = R.layout.item_event

    override fun getViewHolder(
        databinding: ItemEventBinding,
        iOnClick: OnClick<EventDomain>?
    ): EventViewHolder {
        return EventViewHolder(databinding, null)
    }
}
