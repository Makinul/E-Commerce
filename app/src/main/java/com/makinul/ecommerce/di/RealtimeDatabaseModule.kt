package com.makinul.ecommerce.di

import com.makinul.ecommerce.data.repository.AuthRepository
import com.makinul.ecommerce.data.repository.AuthRepositoryImpl
import com.makinul.ecommerce.data.repository.ProductRepository
import com.makinul.ecommerce.data.repository.ProductRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
abstract class RealtimeDatabaseModule {

    @Binds
    abstract fun authRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    abstract fun productRepository(impl: ProductRepositoryImpl): ProductRepository
}