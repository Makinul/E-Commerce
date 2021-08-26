package com.makinul.ecommerce.data.repository

import com.makinul.ecommerce.data.model.Category
import com.makinul.ecommerce.data.model.Product

interface ProductRepository {
    suspend fun allCategories(): List<Category>?
    suspend fun allProducts(): List<Product>?
    suspend fun product(id: String): Product?
}