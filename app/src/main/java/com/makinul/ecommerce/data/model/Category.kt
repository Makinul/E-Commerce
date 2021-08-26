package com.makinul.ecommerce.data.model

data class Category(
    val id: String? = null,
    val name: String? = null,
    val order: Long = 0,
    val url: String? = null,
    val icon: String? = null,
    val hasSubCategory: Boolean = false
)
