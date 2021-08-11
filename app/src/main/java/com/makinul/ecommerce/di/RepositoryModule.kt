package com.makinul.ecommerce.di

import com.makinul.ecommerce.data.repository.UserRepository
import com.makinul.ecommerce.data.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

//    @Binds
//    abstract fun bindRandomRepository(impl: UserRepositoryImpl): UserRepository
}