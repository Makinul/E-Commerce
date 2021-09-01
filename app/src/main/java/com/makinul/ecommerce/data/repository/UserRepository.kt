package com.makinul.ecommerce.data.repository

import com.makinul.ecommerce.data.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUser(userId: String): Flow<User>
}