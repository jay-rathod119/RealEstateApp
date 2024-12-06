package com.solutelabs.realestateapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.solutelabs.realestateapp.databinding.ItemLayoutBinding
import com.solutelabs.realestateapp.fragment.DetailFragment
import com.solutelabs.realestateapp.model.DummyItem
import com.solutelabs.realestateapp.util.replaceFragment

class DummyAdapter(private val dummyItems: List<DummyItem>) :
    RecyclerView.Adapter<DummyAdapter.DummyViewHolder>() {

    inner class DummyViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(dummyItem: DummyItem) {
            binding.layoutTitle.text = dummyItem.title
            binding.layoutAddress.text = dummyItem.address
            binding.layoutImageview.setImageResource(dummyItem.imageResId)
            binding.layoutPrice.text = dummyItem.price
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DummyViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DummyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DummyViewHolder, position: Int) {
        val dummyItem = dummyItems[position]
        holder.bind(dummyItem)

        holder.itemView.setOnClickListener {
            val fragmentManager =
                (holder.itemView.context as AppCompatActivity).supportFragmentManager
            fragmentManager.replaceFragment(
                DetailFragment.getInstance(),
                DetailFragment::class.java.name
            )
        }
    }

    override fun getItemCount(): Int {
        return dummyItems.size
    }
}
