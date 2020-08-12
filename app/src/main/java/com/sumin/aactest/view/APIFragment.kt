package com.sumin.aactest.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.sumin.aactest.R
import com.sumin.aactest.data.User
import com.sumin.aactest.data.UserItems
import com.sumin.aactest.databinding.FragmentApiBinding
import com.sumin.aactest.utilities.InjectorUtils
import com.sumin.aactest.viewmodel.APIViewModel
import kotlinx.android.synthetic.main.fragment_api.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class APIFragment : Fragment() {
    val TAG: String = APIFragment::class.java.simpleName

    private lateinit var binding : FragmentApiBinding
    val model : APIViewModel by activityViewModels{
        InjectorUtils.getAPIViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil
            .inflate(inflater,
                R.layout.fragment_api, container, false)

        binding.vm = model
        binding.lifecycleOwner =  viewLifecycleOwner

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        EventBus.getDefault().register(this)

        val apiAdapter =
            APIFragmentAdapter { item: UserItems, res: Int ->

                when (res) {
                    R.id.mLikeBtn -> {
                        val user = User(
                            item.id.toString(),
                            item.login,
                            item.avatar_url,
                            false
                        )
                        model.addDB(user)
                        Toast.makeText(activity, "${item.login}가 추가 되었습니다.", Toast.LENGTH_SHORT)
                            .show()
                    }

                    R.id.rowRoot -> {
//                        startWebActivity(item.html_url, item.login)
                    }
                }
            }
        mApiRecycler.apply {
            adapter = apiAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onClickEvent(item: UserItems){
        Log.e(TAG, "onClickEvent")
        startWebActivity(item.html_url, item.login)
    }

    fun startWebActivity(htmlUrl: String, title: String){
        val intent = Intent(requireContext(), BaseWebView::class.java)
        intent.putExtra("url", htmlUrl)
        intent.putExtra("title", title)
        startActivity(intent)
        Toast.makeText(activity, "Git으로 이동합니다.", Toast.LENGTH_SHORT).show()
    }
}