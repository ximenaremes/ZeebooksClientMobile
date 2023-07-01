package com.example.zeebooks.feature_home.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import android.util.Base64
import com.example.zeebooks.databinding.ViewCategoryItemGridviewBinding
import com.example.zeebooks.feature_home.domain.model.CategoryModel


class GridCategoriesAdapter :
    ListAdapter<CategoryModel, GridCategoriesAdapter.CategoryViewHolder>(CategoryDiffCallback()) {

    private var onClick: (CategoryModel) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ViewCategoryItemGridviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = getItem(position)
        holder.bind(category)

        holder.itemView.setOnClickListener {
            onClick(category)
        }
    }

    class CategoryViewHolder(private val binding: ViewCategoryItemGridviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: CategoryModel) {
            binding.category = category
            binding.executePendingBindings()

            val baza64Imagine = category.image

            if (baza64Imagine != null) {
                val bytesImagine = Base64.decode(baza64Imagine, Base64.DEFAULT)
                Glide.with(binding.cover.context).load(bytesImagine).into(binding.cover)
            }
        }
    }

    class CategoryDiffCallback : DiffUtil.ItemCallback<CategoryModel>() {
        override fun areItemsTheSame(oldItem: CategoryModel, newItem: CategoryModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CategoryModel, newItem: CategoryModel): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnClick(listener: (CategoryModel) -> Unit) {
        onClick = listener
    }
}
