package com.example.zeebooks.feature_dashboard.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.zeebooks.R
import com.example.zeebooks.commons.domain.model.request.RegisterRequest
import com.example.zeebooks.commons.ui.fragment.BaseFragment
import com.example.zeebooks.commons.utils.Constants
import com.example.zeebooks.commons.utils.Enums
import com.example.zeebooks.databinding.FragmentEditUserDetailsBinding
import com.example.zeebooks.feature_dashboard.viewmodel.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@AndroidEntryPoint
class EditUserDetailsFragment : BaseFragment<FragmentEditUserDetailsBinding>() {

    override val resId = R.layout.fragment_edit_user_details

    private val sharedViewModel: UsersViewModel by navGraphViewModels(R.id.nav_onboarding) {
        defaultViewModelProviderFactory
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            toolbar.iconBack.setOnClickListener { findNavController().navigateUp() }
            toolbar.titleText.setText(R.string.edit_user)

            initObservers()
            checkFocusableInputLastName()
            checkFocusableInputFirstName()
            checkFocusableInputEmail()
            checkFocusableInputPassword()
            checkFocusableInputNumberPhone()

            binding.buttonAddUser.setOnClickListener {
                validateInputs()
            }
        }

    }

    private fun onAddUserButtonClick() {
        val firstName = binding.textFirstName.text.toString()
        val lastName = binding.textName.text.toString()
        val email = binding.textEmail.text.toString()
        val password = binding.passwordValue.text.toString()
        val phoneNumber = binding.numberPhone.text.toString()
        val role = "STANDARD"

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val currentDate = LocalDate.now()
        val dateOfJoin = currentDate.format(formatter)

        val add = RegisterRequest(lastName, firstName, email, password, phoneNumber, role, dateOfJoin)
//        Timber.e("create user $add")
        sharedViewModel.addUser(add)
    }

    private fun validateInputs() {

        when (sharedViewModel.validation(

            lastName = binding.textName.text.toString(),
            firstName = binding.textFirstName.text.toString(),
            email = binding.textEmail.text.toString(),
            password = binding.passwordValue.text.toString(),
            numberPhone = binding.numberPhone.text.toString()
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
                    textFirstName.setBackgroundResource(R.drawable.background_field_error)
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
                    textEmail.setBackgroundResource(R.drawable.background_field_error)
                    imageKey.setColorFilter(resources.getColor(R.color.error, null))
                }
            }
            Constants.VALID_EMAIL_CHARACTERS -> {
                with(binding) {
                    tvPassError.visibility = View.VISIBLE
                    circleErrorEmail.visibility = View.VISIBLE
                    tvPassError.text = context?.getString(Enums.VALID_EMAIL_CHARACTERS.stringResource)
                    tvPassError.setTextColor(resources.getColor(R.color.error, null))
                    textEmail.setBackgroundResource(R.drawable.background_field_error)
                }
            }
            Constants.VALID_PASSWORD -> {
                with(binding) {
                    tvPassConfError.visibility = View.VISIBLE
                    circleError.visibility = View.VISIBLE
                    tvPassConfError.text =
                        context?.getString(Enums.VALID_PASSWORD.stringResource)
                    tvPassConfError.setTextColor(resources.getColor(R.color.error, null))
                    passwordValue.setBackgroundResource(R.drawable.background_field_error)
                }
            }
            Constants.VALID_PASSWORD_LENGTH -> {
                with(binding) {
                    tvPassConfError.visibility = View.VISIBLE
                    circleError.visibility = View.VISIBLE
                    tvPassConfError.text =
                        context?.getString(Enums.VALID_PASSWORD_LENGTH.stringResource)
                    tvPassConfError.setTextColor(resources.getColor(R.color.error, null))
                    passwordValue.setBackgroundResource(R.drawable.background_field_error)
                }
            }
            Constants.VALID_PASSWORD_CHARACTERS -> {
                with(binding) {
                    tvPassConfError.visibility = View.VISIBLE
                    circleError.visibility = View.VISIBLE
                    tvPassConfError.text =
                        context?.getString(Enums.VALID_PASSWORD_CHARACTERS.stringResource)
                    tvPassConfError.setTextColor(resources.getColor(R.color.error, null))
                    passwordValue.setBackgroundResource(R.drawable.background_field_error)
                }
            }
            Constants.VALID_NUMBER_PHONE -> {
                with(binding) {
                    tvPassConfError1.visibility = View.VISIBLE
                    circleErrorConfrimPassword.visibility = View.VISIBLE
                    tvPassConfError1.text =
                        context?.getString(Enums.VALID_NUMBER_PHONE.stringResource)
                    tvPassConfError1.setTextColor(resources.getColor(R.color.error, null))
                    numberPhone.setBackgroundResource(R.drawable.background_field_error)

                }

            }
            Constants.VALID_NUMBER_PHONE_LENGTH -> {
                with(binding) {
                    tvPassConfError1.visibility = View.VISIBLE
                    circleErrorConfrimPassword.visibility = View.VISIBLE
                    tvPassConfError1.text =
                        context?.getString(Enums.VALID_NUMBER_PHONE_LENGTH.stringResource)
                    tvPassConfError1.setTextColor(resources.getColor(R.color.error, null))
                    numberPhone.setBackgroundResource(R.drawable.background_field_error)

                }
            }
            Constants.DEFAULT_VALUE -> {
                changeErrorsVisibility()
                onAddUserButtonClick()
                findNavController().navigateUp()
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
        binding.textFirstName.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                with(binding) {
                    textFirstName.setBackgroundResource(R.drawable.background_field_focus)
                    imageEmail.setColorFilter(resources.getColor(R.color.border, null))
                }
                changeErrorsVisibility()
            } else {
                with(binding) {
                    textFirstName.setBackgroundResource(R.drawable.background_field)
                    imageEmail.setColorFilter(resources.getColor(R.color.border, null))
                }
                changeErrorsVisibility()
            }
        }
    }

    private fun checkFocusableInputEmail() {
        binding.textEmail.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                with(binding) {
                    textEmail.setBackgroundResource(R.drawable.background_field_focus)
                    imageKey.setColorFilter(resources.getColor(R.color.border, null))
                }
                changeErrorsVisibility()
            } else {
                with(binding) {
                    textEmail.setBackgroundResource(R.drawable.background_field)
                    imageKey.setColorFilter(resources.getColor(R.color.border, null))
                }
                changeErrorsVisibility()
            }
        }
    }

    private fun checkFocusableInputPassword() {
        binding.passwordValue.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                with(binding) {
                    passwordValue.setBackgroundResource(R.drawable.background_field_focus)
                    //                    imageConfPassword.setColorFilter(resources.getColor(R.color.border, null))
                }
                changeErrorsVisibility()
            } else {
                with(binding) {
                    passwordValue.setBackgroundResource(R.drawable.background_field)
                    //                    imageConfPassword.setColorFilter(resources.getColor(R.color.border, null))
                }
                changeErrorsVisibility()
            }
        }
    }
    private fun checkFocusableInputNumberPhone() {
        binding.numberPhone.onFocusChangeListener =
            View.OnFocusChangeListener { view, hasFocus ->
                if (hasFocus) {
                    with(binding) {
                        numberPhone.setBackgroundResource(R.drawable.background_field_focus)
                        //                    imageConfPassword.setColorFilter(resources.getColor(R.color.border, null))
                    }
                    changeErrorsVisibility()
                } else {
                    with(binding) {
                        numberPhone.setBackgroundResource(R.drawable.background_field)
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
    private fun initObservers(){
        viewLifecycleOwner.lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){

            }
        }
    }
}