package com.sumin.aactest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sumin.aactest.data.UserItems
import com.sumin.aactest.databinding.RowRxBinding

class RxAdapter(val onClick: (item: UserItems) -> Unit) : RecyclerView.Adapter<UserHolderRx>() {
    lateinit var rowBinding : RowRxBinding
    var userList : List<UserItems> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolderRx {
        rowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_rx, parent, false)
        return UserHolderRx(rowBinding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserHolderRx, position: Int) {
        holder.bind(userList[position], onClick)
    }

    internal fun setUsers(users: List<UserItems>?) {
        userList = users ?: emptyList()

        notifyDataSetChanged()
    }
}

    class UserHolderRx(binding: RowRxBinding) : RecyclerView.ViewHolder(binding.root) {
        val rowBinding = binding

        fun bind(userItem : UserItems, onclick: (item : UserItems) -> Unit){
            rowBinding.user = userItem
            rowBinding.likeBtnRx.setOnClickListener{
                onclick(userItem)
            }

            rowBinding.executePendingBindings()
        }
    }


