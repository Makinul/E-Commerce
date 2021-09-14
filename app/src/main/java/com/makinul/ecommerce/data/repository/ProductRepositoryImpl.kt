package com.makinul.ecommerce.data.repository

import com.google.firebase.database.FirebaseDatabase
import com.makinul.ecommerce.data.local.CategoryDao
import com.makinul.ecommerce.data.local.SubCategoryDao
import com.makinul.ecommerce.data.model.Category
import com.makinul.ecommerce.data.model.Product
import com.makinul.ecommerce.data.model.SubCategory
import com.makinul.ecommerce.util.Resource
import kotlinx.coroutines.flow.collect
import okhttp3.internal.wait
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val database: FirebaseDatabase,
    private val categoryDao: CategoryDao,
    private val subCategoryDao: SubCategoryDao
) : ProductRepository {

    override suspend fun localCategories(): Resource<List<Category>> {
        return try {
            val items = categoryDao.getAll()
            if (items.isEmpty()) {
                Resource.error("no data found", null)
            } else {
                Resource.local(items)
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

                    val items = ArrayList<Category>()
                    for (postSnapshot in snapshot.children) {
                        val item = postSnapshot.getValue(Category::class.java)
                        item?.let {
                            items.add(it)
                            categoryDao.save(it)
                        }
                    }
                    Resource.success(items)
                }
                is DataResponse.Error -> {
                    Resource.error(result.error.message!!, null)
                }
            }
        } catch (e: Exception) {
            Resource.error(e.message!!, null)
        }
    }

    override suspend fun getSubCategories(categoryId: String): Resource<List<SubCategory>> {
        val result = database.reference
            .child("products")
            .child("subCategories")
            .singleValueEvent()
        return try {
            when (result) {
                is DataResponse.Complete -> {
                    val snapshot = result.data

                    val items = ArrayList<SubCategory>()
                    for (postSnapshot in snapshot.children) {
                        val item = postSnapshot.getValue(SubCategory::class.java)
                        item?.let {
                            items.add(it)
                            subCategoryDao.save(it)
                        }
                    }
                    Resource.success(items)
                }
                is DataResponse.Error -> {
                    Resource.error(result.error.message!!, null)
                }
            }
        } catch (e: Exception) {
            Resource.error(e.message!!, null)
        }
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