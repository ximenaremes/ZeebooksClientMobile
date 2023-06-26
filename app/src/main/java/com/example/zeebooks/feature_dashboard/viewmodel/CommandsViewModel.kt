package com.example.zeebooks.feature_dashboard.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.zeebooks.commons.domain.model.User
import com.example.zeebooks.commons.viewmodel.model.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CommandsViewModel @Inject constructor(


) : BaseViewModel() {

    private val _usersLiveData = MutableLiveData<Result<List<User>>>()
    val usersLiveData: LiveData<Result<List<User>>> = _usersLiveData

    init {
    }
}
