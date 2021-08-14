package com.makinul.ecommerce.di

import android.content.Context
import androidx.room.Room
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.makinul.ecommerce.data.local.AppDatabase
import com.makinul.ecommerce.data.local.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "eCommerce.db"
        ).build()
    }

    @Provides
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    @Singleton
    fun provideRealtimeDatabase(): FirebaseDatabase {
        return Firebase.database
    }

//    @Provides
//    fun provideRealtimeDatabaseReference(
//        database: FirebaseDatabase
//    ): DatabaseReference {
//        return database.reference
//    }

    @Provides
    @Singleton
    fun provideFirebaseAuth(@ApplicationContext appContext: Context): FirebaseAuth {
        return Firebase.auth
    }
}