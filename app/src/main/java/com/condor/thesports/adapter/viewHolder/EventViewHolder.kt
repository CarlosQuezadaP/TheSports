package com.condor.thesports.adapter.viewHolder

import com.condor.domain.models.EventDomain
import com.condor.thesports.adapter.viewHolder.base.BaseViewHolder
import com.condor.thesports.databinding.ItemEventBinding
import com.condor.thesports.handlers.OnClick

class EventViewHolder(
    private val viewDataBinding: ItemEventBinding,
    private val OnClick: OnClick<EventDomain>?
) :
    BaseViewHolder<EventDomain>(viewDataBinding) {


    override fun bin(data: EventDomain) {
        viewDataBinding.textViewNameEvent.text = data.strEvent
    }

}
