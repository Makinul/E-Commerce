package com.makinul.ecommerce.data.model

data class Product(
    val id: String? = "",
    val name: String? = "",
    val regularPrice: Long = 0,
    val url: String? = "",
    val images: List<String>? = null
)