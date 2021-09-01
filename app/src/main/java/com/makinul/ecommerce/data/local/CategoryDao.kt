package com.makinul.ecommerce.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.makinul.ecommerce.data.model.Category
import com.makinul.ecommerce.data.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Insert(onConflict = REPLACE)
    fun save(category: Category)

    @Query("SELECT * FROM category WHERE id = :categoryId")
    fun load(categoryId: String): Flow<Category>

    @Query("SELECT * FROM category WHERE id = :categoryId and hasSubCategory == 'true'")
    fun hasSubcategory(categoryId: String): Boolean
}