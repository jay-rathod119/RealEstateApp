package com.solutelabs.realestateapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.javafaker.Faker
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.solutelabs.realestateapp.R
import com.solutelabs.realestateapp.adapter.SettingAdapter
import com.solutelabs.realestateapp.databinding.FragmentSettingBinding
import com.solutelabs.realestateapp.model.DummyItem
import com.solutelabs.realestateapp.util.GoogleLoginUtils
import com.solutelabs.realestateapp.util.replaceFragment

class SettingFragment : BaseFragment() {

    private lateinit var binding: FragmentSettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build()
        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSettingBinding.inflate(inflater, container, false)

        binding.recyleviewSetting.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = SettingAdapter(generateDummyData())
        }

        binding.ButtonLogOut.setOnClickListener {
            GoogleLoginUtils.googleSignOut(googleSignInClient, this)
            requireActivity().supportFragmentManager.replaceFragment(
                InitialFragment.getInstance(), InitialFragment::class.java.name
            )
        }

        return binding.root
    }


    private fun generateDummyData(): List<DummyItem> {
        val faker = Faker()
        val dummyItems = mutableListOf<DummyItem>()

        for (i in 1..30) {
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
        fun getInstance(): SettingFragment {
            return SettingFragment()
        }
    }
}