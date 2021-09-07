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

    override suspend fun allCategories(): Resource<List<Category>> {
        val result = database.reference
            .child("products")
            .child("categories")
            .singleValueEvent()
        try {
            if (result is DataResponse.Complete) {
                val snapshot = result.data

                val categories = ArrayList<Category>()
                for (postSnapshot in snapshot.children) {
                    val category = postSnapshot.getValue(Category::class.java)
                    category?.let { categories.add(it) }
                }
                return Resource.success(categories)
            } else if (result is DataResponse.Error) {
                return Resource.error(result.error.message!!, null)
            } else {
                return Resource.error("unknown error", null)
            }
        } catch (e: Exception) {
            return Resource.error(e.message!!, null)
        }
//        when(result) {
//            is ValueEventResponse.Changed -> {
//                val snapshot = result.snapshot
////                ...
//            }
//            is ValueEventResponse.Cancelled -> {
//                val message = result.error.toException().message
////                Log.e(TAG, "Error: $message")
//            }
//            else -> {
//                result.toString()
//            }
//        }
//        categoryDao.save()

//        val result = database.getReference("ref").singleValueEvent()
//        when (result) {
//            is ValueEventResponse.Changed -> {
//                val snapshot = result.snapshot
//            }
//            is ValueEventResponse.Cancelled -> {
//                val message = result.error.toException().message
//                Log.e("TAG", "Error: $message")
//            }
//            else -> {
//                Log.e("TAG", "Unknown error")
//            }
//        }

//        try {
//            val result = database.reference
//                .child("products")
//                .child("categories")
//                .awaitsSingle()
//
//            if (result != null) {
//
//            }
//        } catch (e: Exception) {
//
//        }
//        return null
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