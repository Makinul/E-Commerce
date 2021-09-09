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
    suspend fun save(category: Category)

    @Insert(onConflict = REPLACE)
    suspend fun save(categories: List<Category>)

    @Query("SELECT * FROM category WHERE id = :categoryId")
    fun load(categoryId: String): LiveData<Category>

    @Query("SELECT * FROM category")
    suspend fun getAll(): List<Category>

    @Query("SELECT * FROM category WHERE id = :categoryId and hasSubCategory == 'true'")
    fun hasSubcategory(categoryId: String): Boolean
}