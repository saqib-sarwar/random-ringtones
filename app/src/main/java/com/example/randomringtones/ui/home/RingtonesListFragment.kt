package com.example.randomringtones.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.example.randomringtones.R

class RingtonesListFragment : DialogFragment() {

    companion object {
        fun newInstance(list: HashMap<String, String>)= RingtonesListFragment().apply {
            arguments = bundleOf(
                RINGTONE_BUNDLE to list
            )
        }
    }

    private lateinit var viewModel: RingtonesListViewModel
    private lateinit var ringtoneMap: HashMap<String, String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.ringtones_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RingtonesListViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ringtoneMap = arguments?.getSerializable(RINGTONE_BUNDLE) as HashMap<String, String>
    }

}