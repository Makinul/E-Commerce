package com.makinul.ecommerce.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SubCategory(
    @PrimaryKey val id: String = "",
    val categoryId: String = "",
    val order: Long = 0,
    val url: String? = null,
    val icon: String? = null,
    val name: String? = null
)
