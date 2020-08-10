package com.sumin.aactest.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sumin.aactest.view.APIFragmentAdapter
import com.sumin.aactest.view.LocalFragmentAdapter
import com.sumin.aactest.R
import com.sumin.aactest.view.RxAdapter
import com.sumin.aactest.data.User
import com.sumin.aactest.data.UserItems

@BindingAdapter("app:items")
fun setItems(recyclerView: RecyclerView, items: List<UserItems>?) {
    (recyclerView.adapter as APIFragmentAdapter).setUsers(items)
}

@BindingAdapter("app:rxitems")
fun setRxItems(recyclerView: RecyclerView, items: List<UserItems>?) {
    (recyclerView.adapter as RxAdapter).setUsers(items)
}

@BindingAdapter("app:users")
fun setUsers(recyclerView: RecyclerView, items: List<User>?) {
    (recyclerView.adapter as LocalFragmentAdapter).setUsers(items)
}


@BindingAdapter("app:likeImg")
fun setLikeImage(view: ImageView, isLike: Boolean = false) {
    /*if (isLike) view.setImageResource(R.mipmap.like_on_foreground)
    else view.setImageResource(R.mipmap.like_off_foreground)*/
}

@BindingAdapter("app:profileImg")
fun setProfileImg(view: ImageView?, url: String?) {
    val umgUrl = url ?: ""

    view?.let {
        Glide.with(it.context)
            .load(umgUrl)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(false)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(it)
    }
}
/*

@BindingAdapter("app:itemDeco")
fun setDecoration(view: RecyclerView, space: Float){
    view.addItemDecoration(SpaceItem(space.toInt(), 3))
}*/
