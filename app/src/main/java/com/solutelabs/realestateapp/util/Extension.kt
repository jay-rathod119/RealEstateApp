package com.solutelabs.realestateapp.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.solutelabs.realestateapp.R

fun FragmentManager.replaceFragment(fragment: Fragment, tag: String) {
    val transaction = beginTransaction()
    transaction.replace(R.id.fragment_container_home, fragment, tag)
    transaction.addToBackStack(tag)
    transaction.commit()
}

