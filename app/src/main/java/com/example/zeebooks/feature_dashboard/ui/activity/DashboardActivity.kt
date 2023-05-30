package com.example.zeebooks.feature_dashboard.ui.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.view.forEach
import androidx.databinding.adapters.AdapterViewBindingAdapter.setOnItemSelectedListener
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.activity.BaseActivity
import com.example.zeebooks.databinding.ActivityDashboardBinding
import com.example.zeebooks.feature_dashboard.ui.fragment.CategoriesFragment
import com.example.zeebooks.feature_dashboard.ui.fragment.ExploreFragment
import com.example.zeebooks.feature_dashboard.ui.fragment.ReviewFragment
import com.example.zeebooks.feature_profile.ui.fragment.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetBehavior

class DashboardActivity: BaseActivity() {

    override val contentResId = R.layout.activity_dashboard

    private lateinit var binding: ActivityDashboardBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBottomNavigation()

    }

    private fun setupBottomNavigation() {

        binding = ActivityDashboardBinding.inflate(layoutInflater)

        binding.bottomNavigation.apply{
            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.exploreFragment -> {
                        navigateToFragment(ExploreFragment())
                        true
                    }
                    R.id.categoriesFragment -> {
                        navigateToFragment(CategoriesFragment())
                        true
                    }
                    R.id.profileFragment -> {
                        navigateToFragment(ProfileFragment())
                        true
                    }
                    else -> false
                }
            }
        }
        navController?.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.exploreFragment -> showBottomNav()
                else -> hideBottomNav()
            }
        }
    }

    private fun showBottomNav() {
        binding.bottomNavigation.visibility = View.VISIBLE
    }

    private fun hideBottomNav() {
        binding.bottomNavigation.visibility = View.GONE
    }

    private fun navigateToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_dashboard_items, fragment)
            .commit()
    }

    open val navController: NavController? by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_dashboard) as NavHostFragment?
        return@lazy navHostFragment?.navController
    }


}



