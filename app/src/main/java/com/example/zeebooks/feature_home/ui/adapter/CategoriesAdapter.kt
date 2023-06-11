package com.example.zeebooks.feature_home.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.zeebooks.R
import com.example.zeebooks.feature_home.domain.model.CategoryModel

class CategoriesAdapter(
    private val items: List<CategoryModel>,
) : RecyclerView.Adapter<CategoriesAdapter.CategoryItemViewHolder>() {

    inner class CategoryItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val imageCategory = view.findViewById<ImageView>(R.id.imageContent)
        private val titleCategory = view.findViewById<TextView>(R.id.textTitle)

        fun bind(categoryModel: CategoryModel) {
            imageCategory.setImageResource(categoryModel.image)
            titleCategory.text = categoryModel.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        return CategoryItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_item_category, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}