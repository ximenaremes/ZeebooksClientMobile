package com.example.zeebooks.feature_onboarding.data.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.example.zeebooks.commons.domain.model.User
import com.example.zeebooks.commons.domain.model.request.RegisterRequest
import com.example.zeebooks.feature_onboarding.data.source.OnboardingRemoteDataSource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

import javax.inject.Inject
import kotlin.system.exitProcess

class OnboardingRepositoryImpl @Inject internal constructor(

    var firebaseAuth: FirebaseAuth,
    var firebaseDBRef: DatabaseReference,
    private val onboardingRemoteDataSource: OnboardingRemoteDataSource
) : OnboardingRepository {

    override suspend fun createUser(registerRequest: RegisterRequest): Result<User> {
        return onboardingRemoteDataSource.createUser(registerRequest)
    }

    override suspend fun loginUser(email: String, password: String): Result<User> {
        return onboardingRemoteDataSource.loginUser(email,password)
    }
    override suspend fun registerUserToFirebaseAuth(lastName: String,firstName: String,email: String,password: String, role: String, dateOfJoin: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)

            .addOnCanceledListener {
                this.addUserToFirebaseDatabase(firebaseAuth.currentUser!!.uid,lastName,firstName,email,password, role, dateOfJoin)
            }
    }

    override fun addUserToFirebaseDatabase(
        uid: String,
        lastName: String,
        firstName: String,
        email: String,
        password: String, role: String, dateOfJoin: String) {
//        firebaseDBRef.child("user").child(uid).setValue(User(uid,lastName,firstName,email,password, role, dateOfJoin))
    }

    override suspend fun login(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { _ ->
                Log.d(TAG, "Succes to login user")
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error to login user", exception)
                exitProcess(0)
            }
    }

    override suspend fun signOut() {
        firebaseAuth.signOut()
    }


}
