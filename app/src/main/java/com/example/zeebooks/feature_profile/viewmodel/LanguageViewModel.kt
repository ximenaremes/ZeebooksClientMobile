package com.example.zeebooks.feature_profile.viewmodel

import androidx.constraintlayout.motion.utils.ViewState
import com.example.zeebooks.commons.viewmodel.model.BaseViewModel
import com.example.zeebooks.feature_profile.domain.model.LanguageItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
@HiltViewModel
class LanguageViewModel @Inject constructor() : BaseViewModel()  {

    private val _reconciliationViewState = MutableStateFlow(ReconciliationViewState())
    private val _viewState = MutableStateFlow(ViewState())

    val viewState = _viewState.asStateFlow()
    val reconciliationViewState = _reconciliationViewState.asStateFlow()

    fun checkIfInvoiceClosingReasonIsSelected(invoiceClosingReasonId: Int) : Boolean {
        return invoiceClosingReasonId == (reconciliationViewState.value.invoiceClosingReason?.id ?: NONE_SELECTED)
    }

//    fun setInvoiceClosingReason(reason: LanguageItem?) {
//        _reconciliationViewState.update { viewState ->
//            viewState.copy(
//                invoiceClosingReason = reason
//            )
//        }
//    }

//    fun setInvoiceClosingReason(reason: LanguageItem?) {
//        _reconciliationViewState.update { viewState ->
//            viewState.copy(invoiceClosingReason = reason)
//        }
//        reason?.isSelected = true
//    }

    fun setInvoiceClosingReason(reason: LanguageItem?) {
        _reconciliationViewState.update { viewState ->
            viewState.copy(invoiceClosingReason = reason)
        }
//
//        val selectedLanguageItem = reason ?: return

//        currentList.forEach { languageItem ->
//            languageItem.isSelected = languageItem == selectedLanguageItem
//        }
    }


    data class ReconciliationViewState(
        var invoiceClosingReason: LanguageItem? = null
    )
    companion object {
        private const val NONE_SELECTED = -1
    }
}
