package com.example.zeebooks.feature_onboarding.data.source

import com.example.zeebooks.commons.domain.model.User
import com.example.zeebooks.commons.domain.model.request.RegisterRequest
import com.example.zeebooks.commons.network.ApiService
import com.example.zeebooks.commons.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OnboardingRemoteDataSource @Inject constructor(
    private val apiService: ApiService
){

    suspend fun createUser(registerRequest: RegisterRequest):  Result<User> {
        return withContext(Dispatchers.IO){
            kotlin.runCatching {
                RetrofitClient.apiService.createUser(registerRequest = registerRequest)
            }
        }
    }

    suspend fun loginUser(email: String, password: String): Result<User>{
        return withContext(Dispatchers.IO){
            kotlin.runCatching {
                RetrofitClient.apiService.login(email= email,password= password)
            }
        }
    }
}
