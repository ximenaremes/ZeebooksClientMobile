package com.example.zeebooks.commons.domain.model.response

import com.example.zeebooks.commons.network.ApiError

class ApiResponseError(

    val fieldErrors: List<ApiError>?,
    val globalErrors: List<ApiError>?

)