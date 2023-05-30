package com.example.zeebooks.feature_onboarding.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.zeebooks.R
import com.example.zeebooks.feature_onboarding.domain.model.OnboardingData

class PagerAdapter(
    private val items: List<OnboardingData>,
) : RecyclerView.Adapter<PagerAdapter.OnboardingItemViewHolder>() {

    inner class OnboardingItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val imaageOnboarding = view.findViewById<ImageView>(R.id.imageContent)
        private val textTilte = view.findViewById<TextView>(R.id.textTitle)
        private val textDescription = view.findViewById<TextView>(R.id.textDescription)
//        private val buttonText = view.findViewById<TextView>(R.id.button)

        fun bind(onboardingData: OnboardingData) {
            imaageOnboarding.setImageResource(onboardingData.image)
            textTilte.text = onboardingData.title
            textDescription.text = onboardingData.description
//            buttonText.text = onboardingData.button
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingItemViewHolder {
        return OnboardingItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_item_container_onboarding, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: OnboardingItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}