package com.example.testapp.presentation

import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.testapp.R
import com.example.testapp.databinding.ActivityMainBinding
import com.example.testapp.presentation.adapters.HomePagerAdapter
import com.example.testapp.presentation.fragments.HomeFragment
import com.example.testapp.presentation.fragments.ProfileFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var homePagerAdapter: HomePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvFragmentTitle.text = HOME_TITLE
        setStatusBarColor()
        setupPageAdapter()
    }

    private fun setStatusBarColor() {
        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = Color.WHITE
    }

    private fun setupPageAdapter() {
        with(binding) {
            homePagerAdapter = HomePagerAdapter(supportFragmentManager)

            val fragments = mutableListOf(
                HomeFragment.newInstance(),
                ProfileFragment.newInstance()
            )

            vpHome.apply {
                adapter = homePagerAdapter
                offscreenPageLimit = LOADED_FRAGMENTS
            }

            homePagerAdapter.setData(fragments)

            bnvHomeStore.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.homeFragment -> {
                        tvFragmentTitle.text = HOME_TITLE
                        checkCurrentPosition(
                            HomeFragment.TAB_POSITION
                        )
                        true
                    }

                    R.id.profileFragment -> {
                        tvFragmentTitle.text = PROFILE_TITLE
                        checkCurrentPosition(
                            ProfileFragment.TAB_POSITION
                        )
                        true
                    }
                    else -> {
                        false
                    }
                }
            }
        }
    }

    private fun checkCurrentPosition(position: Int) {
        if (currentTabPosition == position) {
            homePagerAdapter.removeFragment(
                homePagerAdapter.getItem(currentTabPosition),
                currentTabPosition
            )
        } else {
            currentTabPosition = position
            binding.vpHome.currentItem = currentTabPosition
        }
    }

    companion object {
        private const val LOADED_FRAGMENTS = 2
        private var currentTabPosition = 0
        private const val HOME_TITLE = "Home"
        private const val PROFILE_TITLE = "Profile"
    }
}