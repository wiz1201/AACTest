package com.sumin.aactest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sumin.aactest.data.UserItems
import com.sumin.aactest.databinding.RowApiBinding

class APIFragmentAdapter(val onClick: (item: UserItems) -> Unit) :
    RecyclerView.Adapter<UserHolder>() {
    val TAG : String = APIFragmentAdapter::class.java.simpleName

    lateinit var binding: RowApiBinding
    private var userItems: List<UserItems> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_api, parent, false)
        return UserHolder(binding)
    }

    override fun getItemCount(): Int {
        return userItems.size
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bind(userItems[position], onClick)
    }

    internal fun setUsers(users: List<UserItems>?) {
        userItems = users ?: emptyList()

        notifyDataSetChanged()
    }
}

class UserHolder(userBinding : RowApiBinding) : RecyclerView.ViewHolder(userBinding.root) {
    val userHloderBinding = userBinding
    fun bind(userItem : UserItems, onlick: (item: UserItems) -> Unit){
        userHloderBinding.user = userItem
        userHloderBinding.mLikeBtn.setOnClickListener{
            onlick(userItem)
        }
        userHloderBinding.executePendingBindings()
    }
}