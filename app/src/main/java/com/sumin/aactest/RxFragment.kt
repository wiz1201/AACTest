package com.sumin.aactest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.sumin.aactest.databinding.FragmentRxBinding
import com.sumin.aactest.utilities.InjectorUtils
import com.sumin.aactest.viewmodel.APIViewModel
import kotlinx.android.synthetic.main.fragment_rx.*

class RxFragment : Fragment() {
    val TAG: String = APIFragment::class.java.simpleName

    private lateinit var binding : FragmentRxBinding
    val model : APIViewModel by activityViewModels{
        InjectorUtils.getAPIViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_rx, container, false)
        binding.vm = model
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val apiAdapter = APIFragmentAdapter{
            model.clickLike(it.login)
            Toast.makeText(activity, "${it.login}", Toast.LENGTH_SHORT).show()
        }
        mRxRecycler.apply {
            adapter = apiAdapter
        }
    }
}