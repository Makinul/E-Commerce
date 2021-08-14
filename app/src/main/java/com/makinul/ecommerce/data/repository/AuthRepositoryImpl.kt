package com.makinul.ecommerce.data.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {
    override suspend fun loginWithEmail(email: String, password: String): AuthResult? {
        val data = firebaseAuth
            .signInAnonymously()
            .await()
        return data
    }

    override suspend fun loginWithPhone(phoneNumber: String): AuthResult? {
        TODO("Not yet implemented")
    }
}