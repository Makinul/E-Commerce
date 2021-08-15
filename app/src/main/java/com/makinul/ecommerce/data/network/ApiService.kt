package com.makinul.ecommerce.data.network

import com.makinul.ecommerce.data.model.EmployeeResponse
import com.makinul.ecommerce.data.model.Random
import com.makinul.ecommerce.data.model.User
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    /**
     * @GET declares an HTTP GET request
     * @Path("user") annotation on the userId parameter marks it as a
     * replacement for the {user} placeholder in the @GET path
     */
    @GET("/users/{user}")
    suspend fun getUser(@Path("user") userId: String): User

    @GET("api/breeds/image/random")
    suspend fun getRandom(): Random

    @GET("employees")
    suspend fun getEmployees(): Response<EmployeeResponse>
}