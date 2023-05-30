package com.example.zeebooks.feature_onboarding.data.source

import com.example.zeebooks.commons.network.ApiService
import com.example.zeebooks.feature_onboarding.domain.model.User
import javax.inject.Inject

class OnboardingRemoteDataSource @Inject constructor(
    private val apiService: ApiService
){

//    suspend fun createUser(firstName: String, lastName: String): User {
//        return apiService.createUser(User(firstName = firstName, lastName = lastName))
//    }
//    suspend fun createUser(user: User): retrofit2.Response<User> {
//        return apiService.createUser(user)
//    }

}
