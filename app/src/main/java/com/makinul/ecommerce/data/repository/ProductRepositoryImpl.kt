package com.makinul.ecommerce.data.repository

import com.google.firebase.database.FirebaseDatabase
import com.makinul.ecommerce.data.local.CategoryDao
import com.makinul.ecommerce.data.model.Category
import com.makinul.ecommerce.data.model.Product
import kotlinx.coroutines.flow.collect
import okhttp3.internal.wait
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val database: FirebaseDatabase,
    private val categoryDao: CategoryDao
) : ProductRepository {

    override suspend fun allCategories(): List<Category>? {
        val response = database.reference
            .child("products")
            .child("categories")
            .singleValueEvent()
        try {
            response.wait()
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

//        categoryDao.save()
        return null
    }

    override suspend fun allProducts(): List<Product>? {
        val response = database.getReference("products/items").singleValueEvent()

        try {
            response.wait()
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

//        categoryDao
        return null
    }

    override suspend fun product(id: String): Product? {
        val result =
            database.getReference("products/items").child(id).childEventFlow().collect().wait()

        return null
    }
}