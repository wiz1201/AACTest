package com.sumin.aactest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sumin.aactest.data.UserItems
import com.sumin.aactest.databinding.RowApiBinding

class APIFragmentAdapter(
    private val listner: (UserItems, Int) -> Unit
) : RecyclerView.Adapter<UserHolder>() {
    val TAG : String = APIFragmentAdapter::class.java.simpleName

    lateinit var rowBinding: RowApiBinding
    private var userItems: List<UserItems> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        rowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_api, parent, false)
        return UserHolder(rowBinding)
    }

    override fun getItemCount(): Int {
        return userItems.size
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bind(userItems[position], listner)
    }

    internal fun setUsers(users: List<UserItems>?) {
        userItems = users ?: emptyList()

        notifyDataSetChanged()
    }
}

class UserHolder(binding : RowApiBinding) : RecyclerView.ViewHolder(binding.root) {
    val userBinding = binding

    fun bind(userItem : UserItems, listnerr: (item: UserItems, res: Int) -> Unit){
        userBinding.user = userItem
        userBinding.mLikeBtn.setOnClickListener{
            listnerr(userItem, userBinding.mLikeBtn.id)
        }
        userBinding.rowRoot.setOnClickListener{
            listnerr(userItem, userBinding.rowRoot.id)
        }
        userBinding.executePendingBindings()
    }
}