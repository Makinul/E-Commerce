package com.makinul.ecommerce.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    @PrimaryKey val id: String = "",
    val name: String? = null,
    val order: Long = 0,
    val url: String? = null,
    val icon: String? = null,
    val hasSubCategory: Boolean = false
)
