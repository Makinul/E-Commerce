package com.makinul.ecommerce.data.repository

import com.makinul.ecommerce.data.model.Category
import com.makinul.ecommerce.data.model.Product
import com.makinul.ecommerce.data.model.SubCategory
import com.makinul.ecommerce.util.Resource

interface ProductRepository {
    suspend fun allCategories(): Resource<List<Category>>
    suspend fun localCategories(): Resource<List<Category>>
    suspend fun getSubCategories(categoryId: String): Resource<List<SubCategory>>
    suspend fun allProducts(): List<Product>?
    suspend fun product(id: String): Product?
}