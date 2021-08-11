package com.makinul.ecommerce.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.makinul.ecommerce.data.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = REPLACE)
    fun save(user: User)

    @Query("SELECT * FROM user WHERE userId = :userId")
    fun load(userId: String): Flow<User>

    @Query("SELECT * FROM user WHERE entryTime < :time")
    fun hasUser(time: Long): Boolean
}