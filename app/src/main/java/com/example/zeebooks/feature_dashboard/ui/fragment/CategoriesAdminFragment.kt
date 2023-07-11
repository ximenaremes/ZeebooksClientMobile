package com.example.zeebooks.feature_dashboard.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupWindow
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.databinding.FragmentCategoriesAdminBinding
import com.example.zeebooks.feature_dashboard.ui.adapter.CategoriesListAdapter
import com.example.zeebooks.feature_home.viewmodel.CategoriesViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CategoriesAdminFragment : BaseFragment<FragmentCategoriesAdminBinding>() {

    override val resId = R.layout.fragment_categories_admin

    private val categoriesListAdapter = CategoriesListAdapter()

    private val sharedViewModel: CategoriesViewModel by navGraphViewModels(R.id.nav_onboarding) {
        defaultViewModelProviderFactory
    }

    private fun observe() {

        sharedViewModel.categoriesLiveData.observe(viewLifecycleOwner) { result ->
            if (result.isSuccess) {
                val users = result.getOrNull()
                users?.let {
                    categoriesListAdapter.submitList(it)
                }
            } else {
                result.exceptionOrNull()
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            toolbar.iconBack.setOnClickListener { findNavController().navigateUp() }

            addCategories.setOnClickListener { findNavController().navigate(R.id.action_categoriesAdminFragment_to_addCategoriesFragment) }

        }

        binding.allCategories.adapter = categoriesListAdapter
        observe()

        sharedViewModel.getAllCategories()

        categoriesListAdapter.setOnDeleteClickListener { categoryId ->
            showDialog(categoryId)
        }

        categoriesListAdapter.setOnEditClickListener {
            findNavController().navigate(R.id.action_categoriesAdminFragment_to_editCategoriesDetailsFragment)
//                        categoryId ->
//            sharedViewModel.deleteCategoryById(categoryId)
        }

    }
    private fun showDialog(categoryId: String) {
        val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = inflater.inflate(R.layout.view_custom_popup_logout, null)

        val popupWindow = PopupWindow(
            popupView,
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        popupWindow.isOutsideTouchable = true
        popupWindow.isFocusable = true

        val title = popupView.findViewById<TextView>(R.id.logoutText)
        val message = popupView.findViewById<TextView>(R.id.messageText)
        title.setText(R.string.delete_user)
        message.setText(R.string.delete_message_category)

        val btnCancel = popupView.findViewById<TextView>(R.id.btn_cancel)
        val btnConfirm = popupView.findViewById<TextView>(R.id.btn_confirm)

        btnCancel.setOnClickListener {
            popupWindow.dismiss()
        }
        btnConfirm.setOnClickListener {
            sharedViewModel.deleteCategoryById(categoryId)
            popupWindow.dismiss()
        }
        popupWindow.showAtLocation(requireView(), Gravity.CENTER, 0, 230)

    }
}