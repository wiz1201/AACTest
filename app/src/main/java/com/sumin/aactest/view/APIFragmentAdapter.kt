package com.sumin.aactest.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.sumin.aactest.R
import com.sumin.aactest.data.UserItems
import com.sumin.aactest.databinding.RowApiBinding

class APIFragmentAdapter(
    private val listner: (UserItems, Int) -> Unit
) : BaseAdapter() {
    val TAG : String = APIFragmentAdapter::class.java.simpleName

    lateinit var rowBinding: RowApiBinding
    private var userItems: List<UserItems> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        rowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.row_api, parent, false)
        return BaseViewHolder(rowBinding)
    }

    override fun getItemCount(): Int {
        return userItems.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(userItems[position], listner)
    }


    internal fun setUsers(users: List<UserItems>?) {
        userItems = users ?: emptyList()

        notifyDataSetChanged()
    }
}

class UserHolder(binding : RowApiBinding) : BaseViewHolder(binding) {
    val userBinding = binding
    override fun bind(item: Any, listnerr: (item: Any, res: Int) -> Unit) {
        super.bind(item, listnerr)

        if (item is UserItems){
            userBinding.user = item
            userBinding.mLikeBtn.setOnClickListener{
                listnerr(item, userBinding.mLikeBtn.id)
            }
            userBinding.rowRoot.setOnClickListener{
                listnerr(item, userBinding.rowRoot.id)
            }
            userBinding.executePendingBindings()

        }
    }
}