package com.solutelabs.realestateapp.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import com.solutelabs.realestateapp.databinding.FragmentVerifyOtpBinding

class VerifyOtpFragment : BaseFragment() {


    private lateinit var binding: FragmentVerifyOtpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentVerifyOtpBinding.inflate(inflater, container, false)

        val otpBoxes = arrayOf(
            binding.EditTextOtpBox1,
            binding.EditTextOtpBox2,
            binding.EditTextOtpBox3,
            binding.EditTextOtpBox4
        )



        for (i in otpBoxes.indices) {
            otpBoxes[i].addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    // Not used
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s?.length == 1 && i < otpBoxes.size - 1) {
                        otpBoxes[i + 1].requestFocus()
                    } else if (s?.isEmpty() == true && i > 0) {
                        otpBoxes[i - 1].requestFocus()
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    // Not used
                }
            })
        }

        return binding.root
    }

    companion object {
        fun getInstance(): VerifyOtpFragment {
            return VerifyOtpFragment()
        }
    }
}