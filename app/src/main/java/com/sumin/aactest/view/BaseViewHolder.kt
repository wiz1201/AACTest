package com.sumin.aactest.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

open class BaseViewHolder(
    @LayoutRes res: Int,
    parent: ViewGroup
) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context)
    .inflate(res, parent, false))
{
    val binding: ViewBinding = DataBindingUtil.bind(itemView)!!

    open fun bind(item : Any, listnerr: (item: Any, res: Int) -> Unit){

    }
}