package com.makinul.ecommerce.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey var userId: String = "",
    var userFullName: String? = null,
    var userFirstName: String? = null,
    var userLastName: String? = null,
    var entryTime: Long = 0
)
