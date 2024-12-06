package com.solutelabs.realestateapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solutelabs.realestateapp.databinding.ItemLayoutAllPropertiesBinding
import com.solutelabs.realestateapp.model.DummyItem

class PropertyAdapter(private val properties: List<DummyItem>) :
    RecyclerView.Adapter<PropertyAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemLayoutAllPropertiesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(dummyItem: DummyItem) {
            binding.layoutTitle.text = dummyItem.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLayoutAllPropertiesBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val property = properties[position]
        holder.bind(property)
    }

    override fun getItemCount(): Int {
        return properties.size
    }

}