package com.solutelabs.realestateapp.fragment

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.solutelabs.realestateapp.databinding.BottomSheetDialogFormBinding
import com.solutelabs.realestateapp.databinding.DialogSucessBinding
import com.solutelabs.realestateapp.databinding.FragmentDetailBinding

class DetailFragment : BaseFragment() {

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)


        binding.buttonBuyNow.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(requireContext())
            val bottomSheetViewbinding = BottomSheetDialogFormBinding.inflate(layoutInflater)

            bottomSheetViewbinding.ButtonBuy.setOnClickListener {
                val dialogBinding = DialogSucessBinding.inflate(layoutInflater)

                val dialog = AlertDialog.Builder(requireContext())
                    .setView(dialogBinding.root)
                    .create()

                val progressIndicator = dialogBinding.progressIndicator
                progressIndicator.visibility = View.VISIBLE

                val duration = 2000L // Duration in milliseconds
                val tickInterval = 10L // Interval for progress update in milliseconds
                val progressMax = (duration / tickInterval).toInt()

                object : CountDownTimer(duration, tickInterval) {
                    override fun onTick(millisUntilFinished: Long) {
                        val progress = (progressMax - (millisUntilFinished / tickInterval)).toInt()
                        progressIndicator.setProgressCompat(progress, true)
                    }

                    override fun onFinish() {
                        progressIndicator.setProgressCompat(progressMax, true)
                        dialog.dismiss()
                    }
                }.start()

                dialog.window?.setBackgroundDrawable(ColorDrawable(0))
                dialog.show()
            }

            bottomSheetDialog.setContentView(bottomSheetViewbinding.root)
            bottomSheetDialog.show()

        }

        return binding.root

    }


    companion object {
        fun getInstance(): DetailFragment {
            return DetailFragment()
        }
    }

}