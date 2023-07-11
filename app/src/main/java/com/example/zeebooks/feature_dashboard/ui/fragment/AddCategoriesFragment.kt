package com.example.zeebooks.feature_dashboard.ui.fragment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.bumptech.glide.Glide
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.commons.utils.Constants
import com.example.zeebooks.commons.utils.Enums
import com.example.zeebooks.databinding.FragmentAddCategoriesBinding
import com.example.zeebooks.feature_home.domain.model.CategoryModel
import com.example.zeebooks.feature_home.viewmodel.CategoriesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream

@AndroidEntryPoint
class AddCategoriesFragment : BaseFragment<FragmentAddCategoriesBinding>() {

    override val resId = R.layout.fragment_add_categories

    private var selectedImageUri: Uri? = null

    private val sharedViewModel: CategoriesViewModel by navGraphViewModels(R.id.nav_onboarding) {
        defaultViewModelProviderFactory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            toolbar.iconBack.setOnClickListener { findNavController().navigateUp() }

            initObservers()
            checkFocusableInputNameCategory()

            buttonLoadImage.setOnClickListener {
                val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
                intent.addCategory(Intent.CATEGORY_OPENABLE)
                intent.type = "image/*"
                startActivityForResult(intent, PICK_IMAGE_REQUEST)
            }

            binding.buttonAddCategories.setOnClickListener {
                validateInputs()
                val categoryName = binding.nameCategory.text.toString()

             val imageFile = selectedImageUri?.let { uri ->
                 requireActivity().contentResolver.openInputStream(uri)?.use { inputStream ->
                      val outputStream = ByteArrayOutputStream()
                      inputStream.copyTo(outputStream)
                      val byteArray = outputStream.toByteArray()
                   val requestFile = byteArray.toRequestBody("image/*".toMediaTypeOrNull())
                   MultipartBody.Part.createFormData("imagine", "image.jpg", requestFile)
                  }
                }

                imageFile?.let { file ->
                    sharedViewModel.addCategory(file, categoryName)
             }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            val imageUri: Uri? = data?.data
            selectedImageUri = imageUri
            Glide.with(this)
                .load(imageUri)
                .into(binding.uploadImage)
        }

    }

    private fun validateInputs() {

        when (sharedViewModel.validation(

            name = binding.nameCategory.text.toString(),

        )) {

            Constants.VALID_LASTNAME -> {
                with(binding) {
                    tvNameError.visibility = View.VISIBLE
                    circleErrorLastName.visibility=View.VISIBLE
                    tvNameError.text = context?.getString(Enums.VALID_LASTNAME.stringResource)
                    tvNameError.setTextColor(resources.getColor(R.color.error, null))
                    nameCategory.setBackgroundResource(R.drawable.background_field_error)
                    imageProfile.setColorFilter(resources.getColor(R.color.error, null))
                }
            }
        Constants.DEFAULT_VALUE -> {
            changeErrorsVisibility()

            findNavController().navigateUp()
        }
    }
    }

    private fun changeErrorsVisibility(){

        with(binding) {
            tvNameError.visibility = View.GONE
            circleErrorLastName.visibility=View.GONE
        }
    }

    private fun checkFocusableInputNameCategory() {
        binding.nameCategory.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                with(binding) {
                    nameCategory.setBackgroundResource(R.drawable.background_field_focus)
                    imageProfile.setColorFilter(resources.getColor(R.color.border, null))
                }
                changeErrorsVisibility()
            } else {
                with(binding) {
                    nameCategory.setBackgroundResource(R.drawable.background_field)
                    imageProfile.setColorFilter(resources.getColor(R.color.border, null))
                }
                changeErrorsVisibility()

            }
        }
    }

    private fun initObservers(){
        viewLifecycleOwner.lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){

            }
        }
    }

    companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }


}