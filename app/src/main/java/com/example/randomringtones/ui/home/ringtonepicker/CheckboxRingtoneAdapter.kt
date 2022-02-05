package com.example.randomringtones.ui.home.ringtonepicker

import android.content.Context
import android.media.Ringtone
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.randomringtones.R
import com.example.randomringtones.databinding.ActivityMainBinding.inflate
import com.example.randomringtones.databinding.ListRingtoneCheckboxBinding

class CheckboxAdapter(private val ringtones: List<Ringtone>,
                      private val context: Context?) :
    RecyclerView.Adapter<CheckboxAdapter.RingtoneViewHolder>() {


    class RingtoneViewHolder(private val binding: ListRingtoneCheckboxBinding) :
        RecyclerView.ViewHolder(binding.root){

        fun bind(ringtoneName: String){
            binding.checkedRingtoneName.text = ringtoneName
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RingtoneViewHolder {
        val binding: ListRingtoneCheckboxBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_ringtone_checkbox,
            parent, false)
        return RingtoneViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RingtoneViewHolder, position: Int) {
        val ringtone = ringtones[position]
        val ringtoneName = ringtone.getTitle(context)
        holder.bind(ringtoneName)
    }

    /*fun addAll(items: List<Ringtone>?) {
        requireNotNull(items) { "Cannot add `null` items to the Recycler adapter" }
        ringtones = items
        notifyItemRangeInserted(ringtones.size - items.size, items.size)
    }*/

    override fun getItemCount() = ringtones.size
}