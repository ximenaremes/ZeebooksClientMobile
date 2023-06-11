package com.example.zeebooks.feature_onboarding.data.source

import com.example.zeebooks.commons.domain.model.request.LoginRequest
import com.example.zeebooks.commons.domain.model.request.RegisterRequest
import com.example.zeebooks.commons.domain.model.response.LoginResponse
import com.example.zeebooks.commons.domain.model.response.RegisterResponse
import com.example.zeebooks.commons.network.ApiService
import com.example.zeebooks.commons.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OnboardingRemoteDataSource @Inject constructor(
    private val apiService: ApiService
){

    suspend fun createUser(registerRequest: RegisterRequest): Result<RegisterResponse> {
        return withContext(Dispatchers.IO){
            kotlin.runCatching {
                RetrofitClient.apiService.createUser(registerRequest = registerRequest)
            }
        }
    }

    suspend fun loginUser(loginRequest: LoginRequest): Result<LoginResponse> {
        return withContext(Dispatchers.IO){
            kotlin.runCatching {
                RetrofitClient.apiService.login(loginRequest= loginRequest)
            }
        }
    }
}
