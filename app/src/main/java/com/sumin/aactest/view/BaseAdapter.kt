package com.sumin.aactest.view

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

open class BaseAdapter(
    @LayoutRes private val res: Int
): RecyclerView.Adapter<BaseViewHolder>() {
    var list = mutableListOf<Any>()
    var ItemViewType: Int = 0
    private val HOLDER = 1

    private var headerSize: Int = 0
    @LayoutRes
    var headerLayoutResId: Int? = null
        set(value) {
            if (value == field) {
                return
            }
            field = value
            headerSize = if (value == null) 0 else 1
        }
    var headerItem: Any? = null

    private var footerSize: Int = 0
    @LayoutRes
    var footerLayoutResId: Int? = null
        set(value) {
            if (value == field) {
                return
            }
            field = value
            footerSize = if (value == null) 0 else 1
        }
    var footerItem: Any? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = BaseViewHolder(viewType, parent)

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        when{
            isHeaderPosition(position) ->{
                return headerLayoutResId!!
            }

            isFooterPosition(position) -> {
                return footerLayoutResId!!
            }

            else -> {
                return 1
            }
        }
    }

    private fun isHeaderPosition(position: Int) = headerSize != 0 && position == 0

    private fun isFooterPosition(position: Int) = footerSize != 0 && position == itemCount - 1

    open fun setItems(items: Any?){
//        list = items ?: emptyList()

        notifyDataSetChanged()
    }
}