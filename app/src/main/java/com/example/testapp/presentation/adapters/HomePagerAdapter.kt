package com.example.testapp.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter

class HomePagerAdapter(manager: FragmentManager) :
    FragmentPagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragments = mutableListOf<Fragment>()

    private val homeFragments = mutableListOf<Fragment>()
    private val profileFragments = mutableListOf<Fragment>()

    fun setData(dataFragment: MutableList<Fragment>) {
        fragments.clear()
        fragments.addAll(dataFragment)
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Fragment = when (position) {
        HOME_POSITION -> {
            if (homeFragments.isEmpty()) {
                fragments[position]
            } else {
                homeFragments[homeFragments.size - 1]
            }
        }
        PROFILE_POSITION -> {
            if (profileFragments.isEmpty()) {
                fragments[position]
            } else {
                profileFragments[profileFragments.size - 1]
            }
        }
        else -> {
            fragments[position]
        }
    }

    override fun getCount(): Int = fragments.size

    override fun getItemId(position: Int): Long {
        return when {
            position == HOME_POSITION && getItem(position) == fragments[position] -> {
                HOME_POSITION.toLong()
            }
            position == PROFILE_POSITION && getItem(position) == fragments[position] -> {
                PROFILE_POSITION.toLong()
            }
            else -> {
                getItem(position).hashCode().toLong()
            }
        }
    }

    override fun getItemPosition(`object`: Any): Int = PagerAdapter.POSITION_NONE

    fun removeFragment(fragment: Fragment, position: Int): Boolean = when (position) {
        HOME_POSITION -> {
            if (homeFragments.contains(fragment)) {
                removeInnerFragment(fragment, homeFragments)
                true
            } else {
                false
            }
        }
        PROFILE_POSITION -> {
            if (profileFragments.contains(fragment)) {
                removeInnerFragment(fragment, profileFragments)
                true
            } else {
                false
            }
        }
        else -> {
            false
        }
    }

    private fun removeInnerFragment(fragment: Fragment, tabFragments: MutableList<Fragment>) {
        tabFragments.remove(fragment)
        notifyDataSetChanged()
    }

    companion object {
        const val HOME_POSITION = 0
        const val PROFILE_POSITION = 1
    }
}
