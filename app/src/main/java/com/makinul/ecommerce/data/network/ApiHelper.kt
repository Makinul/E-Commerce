package com.makinul.ecommerce.data.network

import com.makinul.ecommerce.data.model.EmployeeResponse
import retrofit2.Response

interface ApiHelper {
    suspend fun getEmployees(): Response<EmployeeResponse>
}