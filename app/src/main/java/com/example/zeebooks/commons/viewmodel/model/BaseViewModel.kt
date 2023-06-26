package com.example.zeebooks.commons.viewmodel.model


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zeebooks.commons.domain.model.response.RegisterResponse
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel(), CoroutineScope {


    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job + coroutineExceptionHandler

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean>
        get() = _isLoading


    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable>
        get() = _error

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        handleError(throwable)
    }

   protected open fun handleError(throwable: Throwable) {
    _isLoading.value = false
    _error.value = throwable
}

protected fun handleSuccess(result: RegisterResponse) {
    _isLoading.value = false
}

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}
