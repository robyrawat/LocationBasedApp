package com.locationBasedApplication.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(location: LocationEntity): Long

    @Update
    suspend fun update(location: LocationEntity): Int

    @Delete
    suspend fun delete(location: LocationEntity): Int

    @Query("SELECT * FROM locations")
    fun getAllLocations(): Flow<List<LocationEntity>>
}
