package com.example.zeebooks.feature_dashboard.ui.fragment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.bumptech.glide.Glide
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.databinding.FragmentAddBookBinding
import com.example.zeebooks.databinding.FragmentAddCategoriesBinding
import com.example.zeebooks.feature_home.viewmodel.BookViewModel
import com.example.zeebooks.feature_home.viewmodel.CategoriesViewModel

class AddBookFragment : BaseFragment<FragmentAddBookBinding>() {

    override val resId = R.layout.fragment_add_book

    private var selectedImageUri: Uri? = null

    private val sharedViewModel: BookViewModel by navGraphViewModels(R.id.nav_onboarding) {
        defaultViewModelProviderFactory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            toolbar.iconBack.setOnClickListener { findNavController().navigateUp() }


            buttonLoadImage.setOnClickListener {
                val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
                intent.addCategory(Intent.CATEGORY_OPENABLE)
                intent.type = "image/*"
                startActivityForResult(intent, AddBookFragment.PICK_IMAGE_REQUEST)
            }

            binding.buttonAddCategories.setOnClickListener {

//                val categoryName = binding.textLastName.text.toString()
//                val imageFile = selectedImageUri?.let { uri ->
//                    requireActivity().contentResolver.openInputStream(uri)?.use { inputStream ->
//                        val outputStream = ByteArrayOutputStream()
//                        inputStream.copyTo(outputStream)
//                        val byteArray = outputStream.toByteArray()
//                        val requestFile = byteArray.toRequestBody("image/*".toMediaTypeOrNull())
//                        MultipartBody.Part.createFormData("imagine", "image.jpg", requestFile)
//                    }
            }
//
//                imageFile?.let { file ->
//                    val categoryModel = CategoryModel(categoryName)
//                    sharedViewModel.addCategory(file, categoryModel)
//                }
//            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == AddBookFragment.PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            val imageUri: Uri? = data?.data
            selectedImageUri = imageUri
            Glide.with(this)
                .load(imageUri)
                .into(binding.uploadImage)
        }

    }

    companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }
}