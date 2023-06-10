package com.example.zeebooks.feature_profile.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.databinding.FragmentQuestionsBinding

class QuestionsFragment : BaseFragment<FragmentQuestionsBinding>() {

    override val resId = R.layout.fragment_questions

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            toolbar.iconBack.setOnClickListener {
                findNavController().navigateUp()
            }
            toolbar.titleText.setText(R.string.questions)
        }
    }
}