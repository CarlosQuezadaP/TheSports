package com.condor.thesports.adapter.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.condor.thesports.adapter.diff.BaseItemDiffCallback
import com.condor.thesports.adapter.viewHolder.base.BaseViewHolder
import com.condor.thesports.handlers.OnClick

abstract class BaseAdapter<T : Any, VH : BaseViewHolder<T>, DB : ViewDataBinding> :
    ListAdapter<T, VH>(BaseItemDiffCallback<T>()) {

    open lateinit var databinding: DB

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bin(getItem(position))
    }

    abstract val layoutId: Int

    override fun getItemViewType(position: Int) = layoutId

    override fun getItemCount() = currentList.size

    abstract fun getViewHolder(databinding: DB, iOnClick: OnClick<T>?): VH
}
