package com.example.zeebooks.commons.domain.model

import com.example.zeebooks.commons.network.ApiService
import com.example.zeebooks.commons.network.RetrofitClient
import com.example.zeebooks.feature_dashboard.data.repository.DashboardRepository
import com.example.zeebooks.feature_dashboard.data.repository.DashboardRepositoryImpl
import com.example.zeebooks.feature_home.data.repository.HomeRepository
import com.example.zeebooks.feature_home.data.repository.HomeRepositoryImpl
import com.example.zeebooks.feature_onboarding.data.repository.OnboardingRepository
import com.example.zeebooks.feature_onboarding.data.repository.OnboardingRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideApiService(): ApiService {
        return RetrofitClient.apiService
    }

    @Provides
    @Singleton
    fun provideCardRepository(registerUseCase: OnboardingRepositoryImpl): OnboardingRepository =
        registerUseCase

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideDatabaseReference(): DatabaseReference {
        return FirebaseDatabase.getInstance().reference
    }

    @Provides
    @Singleton
    fun provideHomeRepository(getAllCategoriesUseCase: HomeRepositoryImpl): HomeRepository =
        getAllCategoriesUseCase

    @Provides
    @Singleton
    fun provideDashboardRepository(getAllUsersUseCase: DashboardRepositoryImpl): DashboardRepository =
        getAllUsersUseCase


}
