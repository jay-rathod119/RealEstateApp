package com.solutelabs.realestateapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solutelabs.realestateapp.databinding.ItemLayoutSettingBinding
import com.solutelabs.realestateapp.model.DummyItem


class SettingAdapter(private val SettingList: List<DummyItem>) :
    RecyclerView.Adapter<SettingAdapter.StepViewHolder>() {

    inner class StepViewHolder(private val binding: ItemLayoutSettingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(dummyItem: DummyItem) {
            binding.textviewSettingTitle.text = dummyItem.title
            binding.textviewSettingDescription.text = dummyItem.address
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepViewHolder {
        val binding = ItemLayoutSettingBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return StepViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StepViewHolder, position: Int) {
        val setting = SettingList[position]
        holder.bind(setting)


    }

    override fun getItemCount(): Int {
        return SettingList.size
    }
}
