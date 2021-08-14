package com.makinul.ecommerce.data.repository

import com.makinul.ecommerce.data.model.Product

interface ProductRepository {
    suspend fun allProducts(): List<Product>?
    suspend fun product(id: String): Product?
}