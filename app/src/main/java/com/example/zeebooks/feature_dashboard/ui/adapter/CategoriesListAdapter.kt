package com.example.zeebooks.feature_dashboard.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import android.util.Base64
import com.example.zeebooks.databinding.ViewCategoryItemBinding
import com.example.zeebooks.feature_home.domain.model.BookModel
import com.example.zeebooks.feature_home.domain.model.CategoryModel


class CategoriesListAdapter :
    ListAdapter<CategoryModel, CategoriesListAdapter.CategoryViewHolder>(CategoryDiff) {

    private var selectedItem = POSITION_NONE
    private var itemSelectedListener: ((CategoryModel?) -> Unit)? = null
    private var deleteClickListener: ((String) -> Unit)? = null
    private var editClickListener: ((String) -> Unit)? = null

    inner class CategoryViewHolder(val viewBinding: ViewCategoryItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(category: CategoryModel) {
            viewBinding.category = category
            viewBinding.executePendingBindings()

            val baza64Imagine = category.image

            if (baza64Imagine != null) {
                val bytesImagine = Base64.decode(baza64Imagine, Base64.DEFAULT)
                Glide.with(viewBinding.imageCategory.context).load(bytesImagine)
                    .into(viewBinding.imageCategory)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CategoriesListAdapter.CategoryViewHolder {
        val binding =
            ViewCategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }


    override fun onBindViewHolder(holder: CategoriesListAdapter.CategoryViewHolder, position: Int) {
        val book = getItem(position)
        holder.bind(book)

        holder.viewBinding.iconDelete.setOnClickListener {
            val categoryId = getItem(position)?.id
            if (categoryId != null) {
                deleteClickListener?.invoke(categoryId)
            }
        }

        holder.viewBinding.iconEditDetails.setOnClickListener {
            val categoryId = getItem(position)?.id
            if (categoryId != null) {
                editClickListener?.invoke(categoryId)
            }
        }
    }

    fun setOnEditClickListener(listener: (String) -> Unit) {
        editClickListener = listener
    }


    fun setOnDeleteClickListener(listener: (String) -> Unit) {
        deleteClickListener = listener
    }

    fun setOnItemSelectedListener(listener: (CategoryModel?) -> Unit) {
        itemSelectedListener = listener
    }

    private fun selectItem(index: Int) {
        if (selectedItem != POSITION_NONE) notifyItemChanged(selectedItem)
        selectedItem = index
        notifyItemChanged(selectedItem)
    }

    private object CategoryDiff : DiffUtil.ItemCallback<CategoryModel>() {
        override fun areContentsTheSame(
            oldItem: CategoryModel,
            newItem: CategoryModel
        ): Boolean =
            oldItem == newItem

        override fun areItemsTheSame(
            oldItem: CategoryModel,
            newItem: CategoryModel
        ): Boolean =
            oldItem == newItem
    }

    companion object {
        const val POSITION_NONE = -1
    }


}

