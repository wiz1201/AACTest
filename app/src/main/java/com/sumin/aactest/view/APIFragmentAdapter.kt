package com.sumin.aactest.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sumin.aactest.R
import com.sumin.aactest.data.UserItems
import com.sumin.aactest.databinding.RowApiBinding
import org.greenrobot.eventbus.EventBus

class APIFragmentAdapter(
    private val listner: (UserItems, Int) -> Unit
) : RecyclerView.Adapter<UserHolder>() {
    val TAG : String = APIFragmentAdapter::class.java.simpleName

    lateinit var rowBinding: RowApiBinding
    private var userItems: List<UserItems> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        rowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.row_api, parent, false)
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
    private val TAG = UserHolder::class.java.simpleName

    val userBinding = binding

    fun bind(item: Any, listnerr: (item: UserItems, res: Int) -> Unit) {
        if (item is UserItems){
            userBinding.user = item
            userBinding.mLikeBtn.setOnClickListener{
                EventBus.getDefault().post(item)
//                listnerr(item, userBinding.mLikeBtn.id)
            }
            userBinding.rowRoot.setOnClickListener{
                EventBus.getDefault().post(item)
//                listnerr(item, userBinding.rowRoot.id)
            }
            userBinding.executePendingBindings()

        }
    }
}