package com.example.zeebooks.feature_onboarding.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.commons.utils.Constants
import com.example.zeebooks.commons.utils.Enums
import com.example.zeebooks.databinding.FragmentLoginBinding
import com.example.zeebooks.feature_onboarding.viewmodel.LoginViewModel

class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override val resId = R.layout.fragment_login

    override val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkFocusableInputEmail()
        checkFocusableInputPassword()

        with(binding) {
            login.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
            textForgetPassword.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_resetPasswordFragment)
            }

            textInputEmail.setOnClickListener {
                checkFocusableInputEmail()
            }
            textInputPassword.setOnClickListener {

                checkFocusableInputPassword()

            }

            imageToggle.setOnClickListener {
                if (imageToggle.text.toString() == ".") {
                    textRetine.setText(R.string.retine)
                } else {
                    textRetine.setText(R.string.retinut)
                }


//            CoroutineScope(Dispatchers.IO).launch {
//                try {
//                    val userId = "1"
//                    val user = apiService.getUserById(userId)
//                    Log.d("User", "FirstName: ${user.firstName}, LastName: ${user.lastName}")
//                } catch (e: Exception) {
//                    Log.e("Error", "Failed to fetch user: ${e.message}")
//                }
//            }
            }

            btnLogin.setOnClickListener {
                validateInputs(view)

            }
//

        }
    }
    private fun validateInputs(view: View) {

        when (viewModel.validation(

            binding.textEmail.text.toString(),
            binding.textPassword.text.toString()
        )) {

            Constants.VALID_EMAIL -> {
                with(binding) {
                    tvEmailError.visibility = View.VISIBLE
                    circleErrorEmail.visibility = View.VISIBLE
                    tvEmailError.text = context?.getString(Enums.VALID_EMAIL.stringResource)
                    tvEmailError.setTextColor(resources.getColor(R.color.error, null))
                    textEmail.setBackgroundResource(R.drawable.background_field_error)
                    imageEmail.setColorFilter(resources.getColor(R.color.error, null))
                }
            }
            Constants.VALID_EMAIL_CHARACTERS -> {
                with(binding) {
                    tvEmailError.visibility = View.VISIBLE
                    circleErrorEmail.visibility = View.VISIBLE
                    tvEmailError.text =
                        context?.getString(Enums.VALID_EMAIL_CHARACTERS.stringResource)
                    tvEmailError.setTextColor(resources.getColor(R.color.error, null))
                    textEmail.setBackgroundResource(R.drawable.background_field_error)
                    imageEmail.setColorFilter(resources.getColor(R.color.error, null))
                }
            }
            Constants.VALID_PASSWORD -> {
                with(binding) {
                    tvPasswordError.visibility = View.VISIBLE
                    circleErrorPassword.visibility = View.VISIBLE
                    tvPasswordError.text = context?.getString(Enums.VALID_PASSWORD.stringResource)
                    tvPasswordError.setTextColor(resources.getColor(R.color.error, null))
                    textPassword.setBackgroundResource(R.drawable.background_field_error)
//                    imagePassword.setColorFilter(resources.getColor(R.color.error,null))
                }
            }
            Constants.VALID_PASSWORD_LENGTH -> {
                with(binding) {
                    tvPasswordError.visibility = View.VISIBLE
                    circleErrorPassword.visibility = View.VISIBLE
                    tvPasswordError.text =
                        context?.getString(Enums.VALID_PASSWORD_LENGTH.stringResource)
                    tvPasswordError.setTextColor(resources.getColor(R.color.error, null))
                    textPassword.setBackgroundResource(R.drawable.background_field_error)
//                    imagePassword.setColorFilter(resources.getColor(R.color.error,null))
                }
            }
            Constants.VALID_PASSWORD_CHARACTERS -> {
                with(binding) {
                    tvPasswordError.visibility = View.VISIBLE
                    circleErrorPassword.visibility = View.VISIBLE
                    tvPasswordError.text =
                        context?.getString(Enums.VALID_PASSWORD_CHARACTERS.stringResource)
                    tvPasswordError.setTextColor(resources.getColor(R.color.error, null))
                    textPassword.setBackgroundResource(R.drawable.background_field_error)
//                    imagePassword.setColorFilter(resources.getColor(R.color.error,null))
                }
            }
            Constants.DEFAULT_VALUE -> {
                changeErrorsVisibility(view)
                findNavController().navigate(R.id.action_loginFragment_to_dashboardActivity)
            }
        }
    }

    private fun checkFocusableInputEmail() {

        binding.textEmail.setOnFocusChangeListener(View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                with(binding) {
                    textEmail.setBackgroundResource(R.drawable.background_field_focus)
                    imageEmail.setColorFilter(resources.getColor(R.color.border, null))
                }
                changeErrorsVisibility(view)
            } else {
                with(binding) {
                    textEmail.setBackgroundResource(R.drawable.background_field)
                    imageEmail.setColorFilter(resources.getColor(R.color.border, null))
                }
                changeErrorsVisibility(view)

            }
        })

    }

    private fun checkFocusableInputPassword() {

        binding.textPassword.setOnFocusChangeListener(View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                with(binding) {
                    textPassword.setBackgroundResource(R.drawable.background_field_focus)
//                    imagePassword.setColorFilter(resources.getColor(R.color.border, null))
                }
                changeErrorsVisibility(view)
            } else {
                with(binding) {
                    textPassword.setBackgroundResource(R.drawable.background_field)
//                    imagePassword.setColorFilter(resources.getColor(R.color.border, null))
                }
                changeErrorsVisibility(view)
            }
        })

    }

    private fun changeErrorsVisibility(view: View) {
        with(binding) {

            tvEmailError.visibility = View.GONE
            circleErrorEmail.visibility = View.GONE
            tvPasswordError.visibility = View.GONE
            circleErrorPassword.visibility = View.GONE

        }
    }
}