package com.example.zeebooks.commons.viewmodel.model

data class DataModel<T>(
    var status: DataStatus? = null,
    var data: T? = null,
)


enum class DataStatus {
    SUCCESS,
    LOADING,
    ERROR
}