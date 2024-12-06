package com.solutelabs.realestateapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.solutelabs.realestateapp.R
import com.solutelabs.realestateapp.databinding.FragmentForgotPasswordBinding
import com.solutelabs.realestateapp.databinding.FragmentInitialBinding
import com.solutelabs.realestateapp.databinding.FragmentSignupBinding
import com.solutelabs.realestateapp.util.replaceFragment

class ForgotPasswordFragment : BaseFragment() {

    private lateinit var binding: FragmentForgotPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)

        binding.ButtonSendOTP.setOnClickListener {
            requireActivity().supportFragmentManager.replaceFragment(
                VerifyOtpFragment.getInstance(), VerifyOtpFragment::class.java.name
            )
        }

        return binding.root
    }

    companion object {
        fun getInstance(): ForgotPasswordFragment {
            return ForgotPasswordFragment()
        }
    }

}