package com.makinul.ecommerce.data.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.makinul.ecommerce.data.local.CategoryDao
import com.makinul.ecommerce.data.model.Category
import com.makinul.ecommerce.data.model.Product
import com.makinul.ecommerce.util.Resource
import kotlinx.coroutines.flow.collect
import okhttp3.internal.wait
import javax.inject.Inject


class ProductRepositoryImpl @Inject constructor(
    private val database: FirebaseDatabase,
    private val categoryDao: CategoryDao
) : ProductRepository {

    override suspend fun localCategories(): Resource<List<Category>> {
        return try {
            val categories = categoryDao.getAll()
            if (categories.isEmpty()) {
                Resource.error("no data found", null)
            } else {
                Resource.local(categories)
            }
        } catch (e: Exception) {
            Resource.error("error loading from database", null)
        }
    }

    override suspend fun allCategories(): Resource<List<Category>> {
        val result = database.reference
            .child("products")
            .child("categories")
            .singleValueEvent()
        return try {
            when (result) {
                is DataResponse.Complete -> {
                    val snapshot = result.data

                    val categories = ArrayList<Category>()
                    for (postSnapshot in snapshot.children) {
                        val category = postSnapshot.getValue(Category::class.java)
                        category?.let {
                            categories.add(it)
                            categoryDao.save(it)
                        }
                    }
                    Resource.success(categories)
                }
                is DataResponse.Error -> {
                    Resource.error(result.error.message!!, null)
                }
            }
        } catch (e: Exception) {
            Resource.error(e.message!!, null)
        }
    }

    interface ValueEventListener {
        fun onDataChange(snapshot: DataSnapshot)
        fun onCancelled(error: DatabaseError)
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