package com.solutelabs.realestateapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.javafaker.Faker
import com.solutelabs.realestateapp.R
import com.solutelabs.realestateapp.adapter.DummyAdapter
import com.solutelabs.realestateapp.databinding.FragmentHomeBinding
import com.solutelabs.realestateapp.model.DummyItem
import com.solutelabs.realestateapp.util.replaceFragment

class HomeFragment : BaseFragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.recycleviewPopular.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = DummyAdapter(generateDummyData())
        }

        binding.recycleviewLatest.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = DummyAdapter(generateDummyData())
        }


        binding.textViewPopularSeeAll.setOnClickListener {
            requireActivity().supportFragmentManager.replaceFragment(
                SeeAllFragment.getInstance(), SeeAllFragment::class.java.name
            )
        }


        binding.textviewLatestSeeAll.setOnClickListener {
            requireActivity().supportFragmentManager.replaceFragment(
                SeeAllFragment.getInstance(), SeeAllFragment::class.java.name
            )
        }

//        val commonUserName = GoogleLoginUtils.getUserName(requireContext())
//        binding.TextViewUsername.text = commonUserName

        binding.iconSetting.setOnClickListener {
            requireActivity().supportFragmentManager.replaceFragment(
                SettingFragment.getInstance(), SettingFragment::class.java.name
            )
        }

        return binding.root
    }


    private fun generateDummyData(): List<DummyItem> {
        val faker = Faker()
        val dummyItems = mutableListOf<DummyItem>()

        for (i in 1..10) {
            val title = faker.name().title()
            val address = faker.address().fullAddress().take(25)
            val imageResId = getRandomImageResId()
            val price = "$ " + faker.number().numberBetween(5000, 10000) // Fetch price in dollars
            val dummyItem = DummyItem(title, address, imageResId, price)
            dummyItems.add(dummyItem)
        }

        return dummyItems
    }

    // Function to generate a random image resource ID
    private fun getRandomImageResId(): Int {
        val imageResIds = arrayOf(R.drawable.pic1, R.drawable.pic2, R.drawable.pic1)
        return imageResIds.random()
    }

    companion object {
        fun getInstance(): HomeFragment {
            return HomeFragment()
        }
    }

}