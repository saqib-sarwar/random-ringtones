package com.example.randomringtones.ui.home.ringtonepicker

import android.media.Ringtone
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.randomringtones.R
import com.example.randomringtones.databinding.RingtonesListFragmentBinding
import com.example.randomringtones.ui.home.RINGTONE_BUNDLE

class RingtonesListFragment : DialogFragment() {

    private lateinit var viewModel: RingtonesListViewModel
    private lateinit var ringtones: List<Ringtone>
    private lateinit var ringtoneAdapter: CheckboxAdapter

    private lateinit var binding: RingtonesListFragmentBinding

    private var linearLayoutManager: LinearLayoutManager? = null

    companion object {
        fun newInstance(ringtoneList: List<Ringtone>)= RingtonesListFragment().apply {
            arguments = bundleOf(
                RINGTONE_BUNDLE to ringtoneList
            )
            ringtones = ringtoneList
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.ringtones_list_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        linearLayoutManager = LinearLayoutManager(context)
        ringtoneAdapter = CheckboxAdapter(ringtones, context)
        binding.ringtoneRecycler.layoutManager = linearLayoutManager
        binding.ringtoneRecycler.adapter = ringtoneAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RingtonesListViewModel::class.java)
        // TODO: Use the ViewModel
    }
}