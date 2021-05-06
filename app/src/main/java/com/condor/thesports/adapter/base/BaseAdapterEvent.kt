package com.condor.thesports.adapter.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.condor.thesports.adapter.viewHolder.base.BaseViewHolder
import com.condor.thesports.handlers.OnClick

abstract class BaseAdapterEvent<T : Any, VH : BaseViewHolder<T>, DB : ViewDataBinding>(private val iOnClick: OnClick<T>?) :
    BaseAdapter<T, VH, DB>() {

    abstract override fun getViewHolder(databinding: DB, iOnClick: OnClick<T>?): VH

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        databinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            layoutId,
            parent,
            false
        )
        return getViewHolder(databinding, iOnClick)
    }

}