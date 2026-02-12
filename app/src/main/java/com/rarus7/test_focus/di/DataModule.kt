package com.rarus7.test_focus.di

import android.content.Context
import androidx.room.Room
import com.rarus7.test_focus.data.local.AppDatabase
import com.rarus7.test_focus.data.local.UserDao
import com.rarus7.test_focus.data.repository.UserRepositoryImpl
import com.rarus7.test_focus.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_db"
        ).build()

    @Provides
    fun provideDao(db: AppDatabase) = db.userDao()

    @Provides
    fun provideRepository(
        dao: UserDao
    ): UserRepository = UserRepositoryImpl(dao)
}
