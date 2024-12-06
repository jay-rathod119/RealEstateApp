package com.solutelabs.realestateapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.javafaker.Faker
import com.solutelabs.realestateapp.R
import com.solutelabs.realestateapp.adapter.PropertyAdapter
import com.solutelabs.realestateapp.databinding.FragmentSeeAllBinding
import com.solutelabs.realestateapp.model.DummyItem


class SeeAllFragment : BaseFragment() {


    private lateinit var binding: FragmentSeeAllBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSeeAllBinding.inflate(inflater, container, false)

        binding.recycleviewSeeAllPopular.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = PropertyAdapter(generateDummyData())
        }

        return binding.root
    }

    private fun generateDummyData(): List<DummyItem> {
        val faker = Faker()
        val dummyItems = mutableListOf<DummyItem>()

        for (i in 1..10) {
            val title = faker.name().title()
            val address = faker.address().fullAddress().take(25)
            val imageResId = R.drawable.pic_detail
            val price = "$ " + faker.number().numberBetween(5000, 10000) // Fetch price in dollars
            val dummyItem = DummyItem(title, address, imageResId, price)
            dummyItems.add(dummyItem)
        }

        return dummyItems
    }

    companion object {
        fun getInstance(): SeeAllFragment {
            return SeeAllFragment()
        }
    }


}