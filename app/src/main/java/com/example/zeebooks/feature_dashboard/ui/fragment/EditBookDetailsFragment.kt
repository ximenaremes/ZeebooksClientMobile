package com.example.zeebooks.feature_dashboard.ui.fragment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.databinding.FragmentEditBookDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditBookDetailsFragment : BaseFragment<FragmentEditBookDetailsBinding>() {

    override val resId = R.layout.fragment_edit_book_details

    private var selectedImageUri: Uri? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            toolbar.iconBack.setOnClickListener { findNavController().navigateUp() }
            toolbar.titleText.setText(R.string.edit_book)

            buttonLoadImage.setOnClickListener {
                val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
                intent.addCategory(Intent.CATEGORY_OPENABLE)
                intent.type = "image/*"
                startActivityForResult(intent, EditBookDetailsFragment.PICK_IMAGE_REQUEST)
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

        if (requestCode == EditBookDetailsFragment.PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
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
