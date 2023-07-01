package com.example.zeebooks.feature_dashboard.ui.fragment

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.databinding.FragmentAdminDashboardBinding
import com.example.zeebooks.feature_dashboard.ui.adapter.AdminAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdminDashboardFragment : BaseFragment<FragmentAdminDashboardBinding>() {

    override val resId = R.layout.fragment_admin_dashboard

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pagerAdapter = AdminAdapter(this)

        BottomSheetBehavior.from(binding.overviewBottomSheet.bottomSheet).apply {
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }
        with(binding) {
            toolbar.iconBack.visibility = View.INVISIBLE
            val icon = toolbar.iconEdit
            icon.visibility = View.VISIBLE

            val colorFilter = PorterDuffColorFilter(
                ContextCompat.getColor(requireContext(), R.color.second_blue),
                PorterDuff.Mode.SRC_IN
            )
            icon.setColorFilter(colorFilter)
            icon.setImageResource(R.drawable.ic_profile)
            icon.alpha = 0.7f

            icon.setOnClickListener {
                findNavController().navigate(R.id.action_adminDashboardFragment_to_profileAdminFragment)
            }
            overviewBottomSheet.buttonAllUsers.setOnClickListener {
                findNavController().navigate(R.id.action_adminDashboardFragment_to_usersFragment)
            }
            overviewBottomSheet.buttonAllBooks.setOnClickListener {
                findNavController().navigate(R.id.action_adminDashboardFragment_to_booksFragment)
            }
            overviewBottomSheet.buttonCategories.setOnClickListener {
                findNavController().navigate(R.id.action_adminDashboardFragment_to_categoriesAdminFragment)
            }
            overviewBottomSheet.buttonCommand.setOnClickListener {
                findNavController().navigate(R.id.action_adminDashboardFragment_to_commandsFragment)
            }

            binding.viewPager.adapter = pagerAdapter

            TabLayoutMediator(tabLayout, viewPager) { tabLayout, position ->
                when (position) {
                    FIRST_POSITION -> {
                        tabLayout.setText(R.string.dashboard_button_users)
                    }
                    SECOND_POSITION -> {
                        tabLayout.setText(R.string.dashboard_button_books)
                    }
                    THIRD_POSITION -> {
                        tabLayout.setText(R.string.dashboard_button_categories)
                    }
                }
            }.attach()
        }
    }

    companion object {
        const val FIRST_POSITION = 0
        const val SECOND_POSITION = 1
        const val THIRD_POSITION = 2
        const val MAX_POSITION = 3
    }
}