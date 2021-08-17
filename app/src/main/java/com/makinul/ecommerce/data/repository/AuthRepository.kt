package com.makinul.ecommerce.data.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    suspend fun loginWithEmail(email: String, password: String): AuthResult?
    suspend fun loginWithPhone(phoneNumber: String): AuthResult?
    suspend fun anonymousLogin(): AuthResult?
    suspend fun getFirebaseUser(): FirebaseUser?
}