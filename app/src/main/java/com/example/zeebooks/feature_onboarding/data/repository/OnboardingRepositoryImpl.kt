package com.example.zeebooks.feature_onboarding.data.repository


import com.example.zeebooks.feature_onboarding.data.source.OnboardingRemoteDataSource
import com.example.zeebooks.feature_onboarding.domain.model.User
import kotlinx.coroutines.flow.flow

import retrofit2.Response
import java.util.concurrent.Flow
import javax.inject.Inject

class OnboardingRepositoryImpl @Inject internal constructor(
    private val onboardingRemoteDataSource: OnboardingRemoteDataSource
) : OnboardingRepository {

    //    override suspend fun createUser(firstName: String, lastName: String): User {
//        return onboardingRemoteDataSource.createUser(firstName, lastName)
//    }
//
//        override fun createUser(user: User): Flow<Response<User>>{
//            return flow {
//                emit(Result.Loading)
//                val result = userDataSource.createUser(user)
//                if (result is Result.Success) {
//                    emit(Result.Success(Unit))
//                } else if (result is Result.Error) {
//                    emit(Result.Error(result.message))
//                }
//            }.catch { throwable ->
//                emit(Result.Error(throwable.localizedMessage))
//            }
//        }
    override suspend fun createUser(firstName: String, lastName: String): Response<User> {
        TODO("Not yet implemented")
    }
}
