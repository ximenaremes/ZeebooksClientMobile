package com.example.zeebooks.feature_profile.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.zeebooks.commons.viewmodel.model.BaseViewModel
import com.example.zeebooks.feature_onboarding.domain.usecase.SignOutUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val signOutUserUseCase: SignOutUserUseCase,
) : BaseViewModel() {

    fun signOut() {
        viewModelScope.launch {
            signOutUserUseCase.execute()
        }
    }
}