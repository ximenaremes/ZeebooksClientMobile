package com.example.zeebooks.feature_onboarding.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.databinding.FragmentGetStartedBinding
import com.example.zeebooks.feature_onboarding.domain.model.OnboardingData
import com.example.zeebooks.feature_onboarding.ui.adapter.PagerAdapter

class GetStartedFragment : BaseFragment<FragmentGetStartedBinding>() {

    private lateinit var pagerAdapter: PagerAdapter

    override val resId = R.layout.fragment_get_started
    var position = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnboardingItems()
        navigateToRegister()
    }

    private fun setOnboardingItems() {
        pagerAdapter = PagerAdapter(
            listOf(

                (OnboardingData(
                    title = "Descoperă lumea cărților",
                    description = "Lasă-te învăluit în magia cărților și explorează o varietate de genuri literare, de la aventuri și romane de dragoste, până la cărți de dezvoltare personală și non-ficțiune.",
                    image = R.drawable.ic_background_viewpager3_onboarding
                )),
                (OnboardingData(
                    title = "O aventură pentru fiecare",
                    description = "Indiferent dacă îți place să călătorești în locuri îndepărtate sau să explorezi lumea imaginară a personajelor, biblioteca noastră îți oferă o mulțime de cărți care să te ajute să evadezi din cotidian.",
                    image = R.drawable.ic_background_viewpager4_onboarding
                )),
                (OnboardingData(
                    title = "Cărți pentru dezvoltarea personală",
                    description = "Cărțile sunt o modalitate excelentă de a-ți dezvolta abilitățile și de a învăța lucruri noi. Descoperă cărțile noastre de dezvoltare personală care îți vor oferi inspirație și îți vor ajuta să devii cea mai bună versiune a ta.",
                    image = R.drawable.ic_background_viewpager5_onboarding
                )),
                (OnboardingData(
                    title = "Explorează noi lumi",
                    description = "Descoperă lumi noi prin lectură. Biblioteca noastră este plină de cărți care îți vor oferi oportunitatea să explorezi noi culturi și să înțelegi perspective diferite.",
                    image = R.drawable.ic_background_viewpager1_onboarding
                ))
            )
        )
        binding.viewPager.adapter = pagerAdapter
        binding.wormDot.attachTo(binding.viewPager)
    }

    private fun navigateToRegister() {
        position = binding.viewPager.currentItem
//        val index = binding.viewPager.currentItem +1

       binding.buttonRegister.setOnClickListener {
            if (binding.viewPager.currentItem + 1 < pagerAdapter.itemCount) {
                binding.viewPager.currentItem += 1
            } else {
                findNavController().navigate(R.id.action_getStartedFragment_to_loginFragment)
            }
       }

        binding.textSkip.setOnClickListener {
            findNavController().navigate(R.id.action_getStartedFragment_to_loginFragment)
        }

//        if (position < pagerAdapter.itemCount){
//            position +1
//            binding.viewPager.currentItem = position
////            binding.buttonRegister.text = "Get"
        }

    }