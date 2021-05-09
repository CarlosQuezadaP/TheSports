package com.condor.thesports.adapter.diff

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

@SuppressLint("DiffUtilEquals")
class BaseItemDiffCallback<T : Any> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.toString() == newItem.toString()
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }
}