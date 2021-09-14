package com.makinul.ecommerce.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.makinul.ecommerce.data.model.Category
import com.makinul.ecommerce.data.model.SubCategory

@Dao
interface SubCategoryDao {
    @Insert(onConflict = REPLACE)
    suspend fun save(item: SubCategory)

    @Insert(onConflict = REPLACE)
    suspend fun save(items: List<SubCategory>)

    @Query("SELECT * FROM subcategory WHERE id = :id")
    fun load(id: String): LiveData<SubCategory>

    @Query("SELECT * FROM subcategory")
    suspend fun getAll(): List<SubCategory>
}