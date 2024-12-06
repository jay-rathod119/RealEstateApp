package com.solutelabs.realestateapp.fragment

import LoginFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.solutelabs.realestateapp.databinding.FragmentInitialBinding
import com.solutelabs.realestateapp.util.replaceFragment

class InitialFragment : BaseFragment() {

    private lateinit var binding: FragmentInitialBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInitialBinding.inflate(inflater, container, false)

        binding.ButtonLetsStart.setOnClickListener {
            requireActivity().supportFragmentManager.replaceFragment(
                HomeFragment.getInstance(), HomeFragment::class.java.name
            )
        }

        binding.ButtonLogin.setOnClickListener {
            requireActivity().supportFragmentManager.replaceFragment(
                LoginFragment.getInstance(), LoginFragment::class.java.name
            )
        }

        return binding.root
    }

    companion object {
        fun getInstance(): InitialFragment {
            return InitialFragment()
        }
    }

}