package com.sumin.aactest.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sumin.aactest.R
import com.sumin.aactest.data.User
import com.sumin.aactest.databinding.RowLocalBinding

class LocalFragmentAdapter() :
    RecyclerView.Adapter<LocalUserHolder>() {
    val TAG : String = LocalFragmentAdapter::class.java.simpleName

    lateinit var binding : RowLocalBinding
    private var userItems : List<User> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocalUserHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.row_local, parent, false)
        return LocalUserHolder(binding)
    }

    override fun getItemCount(): Int {
        return userItems.size
    }

    override fun onBindViewHolder(holder: LocalUserHolder, position: Int) {
        holder.bind(userItems[position])
    }

    internal fun setUsers(users: List<User>?) {
        userItems = users ?: emptyList()

        notifyDataSetChanged()
    }
}

class LocalUserHolder(userBinding : RowLocalBinding) : RecyclerView.ViewHolder(userBinding.root) {
    val userHloderBinding = userBinding

    fun bind(userItem : User/*, onlick: (item: UserItems) -> Unit*/){
        userHloderBinding.user = userItem
        userHloderBinding.executePendingBindings()
    }
}