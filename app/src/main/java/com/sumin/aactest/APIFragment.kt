package com.sumin.aactest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.sumin.aactest.data.User
import com.sumin.aactest.databinding.FragmentApiBinding
import com.sumin.aactest.utilities.InjectorUtils
import com.sumin.aactest.viewmodel.APIViewModel
import kotlinx.android.synthetic.main.fragment_api.*

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
            .inflate(inflater, R.layout.fragment_api, container, false)

        binding.vm = model
        binding.lifecycleOwner =  viewLifecycleOwner

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val apiAdapter = APIFragmentAdapter{
            val user = User(
                it.id.toString(),
                it.login,
                it.avatar_url,
                false
            )
            model.addDB(user)
            Toast.makeText(activity, "${it.login}가 추가 되었습니다.", Toast.LENGTH_SHORT).show()
        }
        mApiRecycler.apply {
            adapter = apiAdapter
        }
    }
}