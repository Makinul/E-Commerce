package com.makinul.ecommerce.data.repository

import com.makinul.ecommerce.data.model.EmployeeResponse
import com.makinul.ecommerce.data.network.ApiHelper
import retrofit2.Response
import javax.inject.Inject

class EmployeeRepository @Inject constructor(
    private val apiHelper: ApiHelper
) {
    suspend fun getEmployees(): Response<EmployeeResponse> = apiHelper.getEmployees()
}