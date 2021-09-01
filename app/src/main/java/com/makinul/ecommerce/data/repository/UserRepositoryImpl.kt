package com.makinul.ecommerce.data.repository

import com.makinul.ecommerce.data.local.UserDao
import com.makinul.ecommerce.data.model.User
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {

    override suspend fun getUser(userId: String): Flow<User> {
        refreshUser(userId)
        // Returns a Flow object directly from the database.
        return userDao.load(userId)
    }

    private suspend fun refreshUser(userId: String) {
        // Check if user data was fetched recently.
        val userExists = userDao.hasUser(FRESH_TIMEOUT)
        if (!userExists) {

        }
    }

    companion object {
        val FRESH_TIMEOUT = TimeUnit.DAYS.toMillis(1)
    }
}