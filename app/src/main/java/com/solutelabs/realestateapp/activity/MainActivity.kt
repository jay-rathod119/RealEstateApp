package com.solutelabs.realestateapp.activity

import android.os.Bundle
import com.solutelabs.realestateapp.databinding.ActivityMainBinding
import com.solutelabs.realestateapp.fragment.InitialFragment
import com.solutelabs.realestateapp.util.replaceFragment

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.replaceFragment(
            InitialFragment.getInstance(),
            InitialFragment::class.java.simpleName
        )
    }
}