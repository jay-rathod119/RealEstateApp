package com.solutelabs.realestateapp.fragment

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.solutelabs.realestateapp.databinding.FragmentSignupBinding

class SignupFragment : BaseFragment() {

    private lateinit var binding: FragmentSignupBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignupBinding.inflate(inflater, container, false)


        return binding.root
    }

    companion object {
        fun getInstance(): SignupFragment {
            return SignupFragment()
        }
    }

}