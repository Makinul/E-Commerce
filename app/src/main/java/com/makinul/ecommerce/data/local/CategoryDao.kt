package com.makinul.ecommerce.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.makinul.ecommerce.data.model.Category

@Dao
interface CategoryDao {
    @Insert(onConflict = REPLACE)
    suspend fun save(item: Category)

    @Insert(onConflict = REPLACE)
    suspend fun save(items: List<Category>)

    @Query("SELECT * FROM category WHERE id = :id")
    fun load(id: String): LiveData<Category>

    @Query("SELECT * FROM category")
    suspend fun getAll(): List<Category>

    @Query("SELECT * FROM category WHERE id = :id and hasSubCategory == 'true'")
    fun hasSubcategory(id: String): Boolean
}