package com.example.zeebooks.feature_dashboard.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.zeebooks.feature_dashboard.ui.fragment.AdminDashboardFragment.Companion.FIRST_POSITION
import com.example.zeebooks.feature_dashboard.ui.fragment.AdminDashboardFragment.Companion.MAX_POSITION
import com.example.zeebooks.feature_dashboard.ui.fragment.AdminDashboardFragment.Companion.SECOND_POSITION
import com.example.zeebooks.feature_dashboard.ui.fragment.AdminDashboardFragment.Companion.THIRD_POSITION
import com.example.zeebooks.feature_dashboard.ui.fragment.BookGraphFragment
import com.example.zeebooks.feature_dashboard.ui.fragment.CategoryGraphFragment
import com.example.zeebooks.feature_dashboard.ui.fragment.UserGraphFragment

class AdminAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = MAX_POSITION

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            FIRST_POSITION -> {
                UserGraphFragment()
            }
            SECOND_POSITION -> {
                BookGraphFragment()
            }
            THIRD_POSITION -> {
                CategoryGraphFragment()
            }
            else -> {
                Fragment()
            }
        }
    }
}