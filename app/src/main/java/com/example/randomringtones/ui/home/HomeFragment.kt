package com.example.randomringtones.ui.home

import android.media.RingtoneManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.randomringtones.R
import com.example.randomringtones.databinding.FragmentHomeBinding

const val RINGTONE_BUNDLE = "ringtonesList"

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        binding.pickerButton.setOnClickListener { pickerClicked() }
        return root
    }

    fun pickerClicked(){
        val ringtoneList = getRingtones()
        openDialog(ringtoneList)
    }

    fun openDialog(ringtones : HashMap<String, String>){
        val ringtonesListFragment = RingtonesListFragment.newInstance(ringtones)
        ringtonesListFragment.show(childFragmentManager, "ListFragment")
    }

    private fun getRingtones(): HashMap<String, String> {
        val ringtoneManager =  RingtoneManager(requireContext())
        ringtoneManager.setType(RingtoneManager.TYPE_RINGTONE)
        val ringtoneCursor = ringtoneManager.cursor
        val ringtoneList = HashMap<String, String>()
        while (ringtoneCursor.moveToNext()){
            val ringtoneName = ringtoneCursor.getString(RingtoneManager.TITLE_COLUMN_INDEX)
            /* call ringtoneManager.getRingtone(context, ringtoneUri) */
            val ringtoneUri = ringtoneCursor.getString((RingtoneManager.URI_COLUMN_INDEX)) +
                    '/' + ringtoneCursor.getString(RingtoneManager.ID_COLUMN_INDEX)
            ringtoneList[ringtoneName] = ringtoneUri
        }

        return ringtoneList
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}