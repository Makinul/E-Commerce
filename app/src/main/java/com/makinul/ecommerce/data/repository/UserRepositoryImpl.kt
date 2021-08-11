package com.makinul.ecommerce.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.makinul.ecommerce.data.local.UserDao
import com.makinul.ecommerce.data.model.Random
import com.makinul.ecommerce.data.model.User
import com.makinul.ecommerce.data.network.ApiService
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
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
            // Refreshes the data.
            val response = apiService.getUser(userId)

            // Check for errors here.

            // Updates the database. Since `userDao.load()` returns an object of
            // `Flow<User>`, a new `User` object is emitted every time there's a
            // change in the `User`  table.
            userDao.save(response)
        }
    }

    override suspend fun getRandom(): LiveData<Random> {
        val mutableLiveData = MutableLiveData<Random>()

        try {
            val response = apiService.getRandom()
            Log.v("TAG", response.toString())
        } catch (e: Exception) {
            e.printStackTrace()
        }


//        mutableLiveData.value = response
        return mutableLiveData
    }

    companion object {
        val FRESH_TIMEOUT = TimeUnit.DAYS.toMillis(1)
    }
}