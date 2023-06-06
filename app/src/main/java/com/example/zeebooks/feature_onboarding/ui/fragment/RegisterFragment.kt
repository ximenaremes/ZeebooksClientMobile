package com.example.zeebooks.feature_onboarding.ui.fragment

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.Window
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.commons.utils.Constants
import com.example.zeebooks.commons.utils.Enums
import com.example.zeebooks.databinding.FragmentRegisterBinding
import com.example.zeebooks.feature_onboarding.viewmodel.RegisterViewModel

class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {

    override val resId = R.layout.fragment_register
    override val viewModel: RegisterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            authentication.setOnClickListener {
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }
            termsAndConditions.setOnClickListener {
                findNavController().navigate(R.id.action_registerFragment_to_termsAndConditionsFragment)
            }
            buttonRegister.setOnClickListener {
                validateInputs()
            }
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                buttonRegister.isEnabled = isChecked
            }

            checkFocusableInputLastName()
            checkFocusableInputFirstName()
            checkFocusableInputEmail()
            checkFocusableInputPassword()
            checkFocusableInputConfPassword()
        }
    }

    private fun validateInputs() {

        when (viewModel.validation(

            binding.textName.text.toString(),
            binding.textEmail.text.toString(),
            binding.textPassword.text.toString(),
            binding.textConfPassword.text.toString(),
            binding.textConfPassword1.text.toString()
        )) {

            Constants.VALID_LASTNAME -> {
                with(binding) {
                    tvNameError.visibility = View.VISIBLE
                    circleErrorLastName.visibility=View.VISIBLE
                    tvNameError.text = context?.getString(Enums.VALID_LASTNAME.stringResource)
                    tvNameError.setTextColor(resources.getColor(R.color.error, null))
                    textName.setBackgroundResource(R.drawable.background_field_error)
                    imageProfile.setColorFilter(resources.getColor(R.color.error, null))
                }
            }
            Constants.VALID_FIRSTNAME -> {
                with(binding) {
                    tvEmailError.visibility = View.VISIBLE
                    circleErrorFirstName.visibility = View.VISIBLE
                    tvEmailError.text = context?.getString(Enums.VALID_FIRSTNAME.stringResource)
                    tvEmailError.setTextColor(resources.getColor(R.color.error, null))
                    textEmail.setBackgroundResource(R.drawable.background_field_error)
                    imageEmail.setColorFilter(resources.getColor(R.color.error, null))
                }
            }
            Constants.VALID_EMAIL -> {
                with(binding) {
                    tvPassError.visibility = View.VISIBLE
                    circleErrorEmail.visibility = View.VISIBLE
                    tvPassError.text =
                        context?.getString(Enums.VALID_EMAIL.stringResource)
                    tvPassError.setTextColor(resources.getColor(R.color.error, null))
                    textPassword.setBackgroundResource(R.drawable.background_field_error)
                    imageKey.setColorFilter(resources.getColor(R.color.error, null))
                }
            }
            Constants.VALID_EMAIL_CHARACTERS -> {
                with(binding) {
                    tvPassError.visibility = View.VISIBLE
                    circleErrorEmail.visibility = View.VISIBLE
                    tvPassError.text = context?.getString(Enums.VALID_EMAIL_CHARACTERS.stringResource)
                    tvPassError.setTextColor(resources.getColor(R.color.error, null))
                    textPassword.setBackgroundResource(R.drawable.background_field_error)
//                    imagePassword.setColorFilter(resources.getColor(R.color.error, null))
                }
            }
            Constants.VALID_PASSWORD -> {
                with(binding) {
                    tvPassConfError.visibility = View.VISIBLE
                    circleError.visibility = View.VISIBLE
                    tvPassConfError.text =
                        context?.getString(Enums.VALID_PASSWORD.stringResource)
                    tvPassConfError.setTextColor(resources.getColor(R.color.error, null))
                    textConfPassword.setBackgroundResource(R.drawable.background_field_error)
//                    imagePassword.setColorFilter(resources.getColor(R.color.error, null))
                }
            }
            Constants.VALID_PASSWORD_LENGTH -> {
                with(binding) {
                    tvPassConfError.visibility = View.VISIBLE
                    circleError.visibility = View.VISIBLE
                    tvPassConfError.text =
                        context?.getString(Enums.VALID_PASSWORD_LENGTH.stringResource)
                    tvPassConfError.setTextColor(resources.getColor(R.color.error, null))
                    textConfPassword.setBackgroundResource(R.drawable.background_field_error)
//                    imagePassword.setColorFilter(resources.getColor(R.color.error, null))
                }
            }
            Constants.VALID_PASSWORD_CHARACTERS -> {
                with(binding) {
                    tvPassConfError.visibility = View.VISIBLE
                    circleError.visibility = View.VISIBLE
                    tvPassConfError.text =
                        context?.getString(Enums.VALID_PASSWORD_CHARACTERS.stringResource)
                    tvPassConfError.setTextColor(resources.getColor(R.color.error, null))
                    textConfPassword.setBackgroundResource(R.drawable.background_field_error)
//                    imageConfPassword.setColorFilter(resources.getColor(R.color.error, null))
                }
            }
            Constants.VALID_CONFIRM_PASSWORD -> {
                with(binding) {
                    tvPassConfError1.visibility = View.VISIBLE
                    circleErrorConfrimPassword.visibility = View.VISIBLE
                    tvPassConfError1.text =
                        context?.getString(Enums.VALID_CONFIRM_PASSWORD.stringResource)
                    tvPassConfError1.setTextColor(resources.getColor(R.color.error, null))
                    textConfPassword1.setBackgroundResource(R.drawable.background_field_error)
//                    imageConfPassword.setColorFilter(resources.getColor(R.color.error, null))
                }

            }
            Constants.VALID_CONFIRM_PASSWORD_IDENTICAL -> {
                with(binding) {
                    tvPassConfError1.visibility = View.VISIBLE
                    circleErrorConfrimPassword.visibility = View.VISIBLE
                    tvPassConfError1.text =
                        context?.getString(Enums.VALID_CONFIRM_PASSWORD_IDENTICAL.stringResource)
                    tvPassConfError1.setTextColor(resources.getColor(R.color.error, null))
                    textConfPassword1.setBackgroundResource(R.drawable.background_field_error)
//                    imageConfPassword.setColorFilter(resources.getColor(R.color.error, null))
                }
            }
            Constants.DEFAULT_VALUE -> {
                changeErrorsVisibility()
                showDialog()
                //api calls viewmodel
            }

        }
    }

    private fun checkFocusableInputLastName() {
        binding.textName.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                with(binding) {
                    textName.setBackgroundResource(R.drawable.background_field_focus)
                    imageProfile.setColorFilter(resources.getColor(R.color.border, null))
                }
                changeErrorsVisibility()
            } else {
                with(binding) {
                    textName.setBackgroundResource(R.drawable.background_field)
                    imageProfile.setColorFilter(resources.getColor(R.color.border, null))
                }
                changeErrorsVisibility()

            }
        }
    }

    private fun checkFocusableInputFirstName() {
        binding.textEmail.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                with(binding) {
                    textEmail.setBackgroundResource(R.drawable.background_field_focus)
                    imageEmail.setColorFilter(resources.getColor(R.color.border, null))
                }
                changeErrorsVisibility()
            } else {
                with(binding) {
                    textEmail.setBackgroundResource(R.drawable.background_field)
                    imageEmail.setColorFilter(resources.getColor(R.color.border, null))
                }
                changeErrorsVisibility()
            }
        }
    }

    private fun checkFocusableInputEmail() {
        binding.textPassword.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                with(binding) {
                    textPassword.setBackgroundResource(R.drawable.background_field_focus)
                    imageKey.setColorFilter(resources.getColor(R.color.border, null))
                }
                changeErrorsVisibility()
            } else {
                with(binding) {
                    textPassword.setBackgroundResource(R.drawable.background_field)
                    imageKey.setColorFilter(resources.getColor(R.color.border, null))
                }
                changeErrorsVisibility()
            }
        }
    }

    private fun checkFocusableInputPassword() {
        binding.textConfPassword.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                with(binding) {
                    textConfPassword.setBackgroundResource(R.drawable.background_field_focus)
    //                    imageConfPassword.setColorFilter(resources.getColor(R.color.border, null))
                }
                changeErrorsVisibility()
            } else {
                with(binding) {
                    textConfPassword.setBackgroundResource(R.drawable.background_field)
    //                    imageConfPassword.setColorFilter(resources.getColor(R.color.border, null))
                }
                changeErrorsVisibility()
            }
        }
    }
    private fun checkFocusableInputConfPassword() {
        binding.textConfPassword1.onFocusChangeListener =
            View.OnFocusChangeListener { view, hasFocus ->
                if (hasFocus) {
                    with(binding) {
                        textConfPassword1.setBackgroundResource(R.drawable.background_field_focus)
    //                    imageConfPassword.setColorFilter(resources.getColor(R.color.border, null))
                    }
                    changeErrorsVisibility()
                } else {
                    with(binding) {
                        textConfPassword1.setBackgroundResource(R.drawable.background_field)
    //                    imageConfPassword.setColorFilter(resources.getColor(R.color.border, null))
                    }
                    changeErrorsVisibility()
                }
            }
    }

    private fun changeErrorsVisibility(){

        with(binding) {
            tvNameError.visibility = View.GONE
            circleErrorLastName.visibility=View.GONE
            tvEmailError.visibility = View.GONE
            circleErrorFirstName.visibility = View.GONE
            tvPassError.visibility = View.GONE
            circleErrorEmail.visibility = View.GONE
            tvPassConfError.visibility = View.GONE
            circleError.visibility = View.GONE
            tvPassConfError1.visibility = View.GONE
            circleErrorConfrimPassword.visibility = View.GONE
        }
    }

    private fun showDialog() {
        val dialog = this.context?.let { Dialog(it) }
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setCancelable(false)
        dialog?.setContentView(R.layout.view_custom_popup)

//        val imageView: ImageView = view.findViewById(R.id.animatedImage)
//
//        Glide.with(this)
//            .asGif()
//            .load(R.drawable.ic_login)
//            .into(imageView)

        Handler().postDelayed({
            dialog?.dismiss()

            findNavController().navigate(R.id.action_registerFragment_to_dashboardActivity)

        }, 2500)

        dialog?.show()
    }

}


