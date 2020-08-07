package com.sumin.aactest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.sumin.aactest.databinding.FragmentLocalBinding
import com.sumin.aactest.utilities.InjectorUtils
import com.sumin.aactest.viewmodel.APIViewModel
import kotlinx.android.synthetic.main.fragment_local.*

class LocalFragment : Fragment() {

    private lateinit var binding : FragmentLocalBinding
    val model : APIViewModel by activityViewModels{
        InjectorUtils.getAPIViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_local, container, false)

        binding.vm = model
        binding.lifecycleOwner =  viewLifecycleOwner

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val localAdapter = LocalFragmentAdapter()
        mLocalRecycler.apply {
            adapter = localAdapter
        }
    }
}