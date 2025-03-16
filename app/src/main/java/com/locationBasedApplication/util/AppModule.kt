package com.locationBasedApplication.util

import android.content.Context
import androidx.room.Room
import com.locationBasedApplication.data.AppDatabase
import com.locationBasedApplication.data.LocationDao
import com.locationBasedApplication.repository.LocationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "location_database")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideLocationDao(database: AppDatabase): LocationDao = database.locationDao()

    @Provides
    @Singleton
    fun provideLocationRepository(dao: LocationDao): LocationRepository =
        LocationRepository(dao)
}
