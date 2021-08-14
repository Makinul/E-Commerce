package com.makinul.ecommerce.data.repository

import com.google.firebase.auth.AuthResult

interface AuthRepository {
    suspend fun loginWithEmail(email: String, password: String): AuthResult?
    suspend fun loginWithPhone(phoneNumber: String): AuthResult?
}