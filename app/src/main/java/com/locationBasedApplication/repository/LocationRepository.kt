package com.locationBasedApplication.repository

import com.locationBasedApplication.data.LocationDao
import com.locationBasedApplication.data.LocationEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocationRepository @Inject constructor(
    private val dao: LocationDao
) {
    val locations: Flow<List<LocationEntity>> = dao.getAllLocations()

    suspend fun addLocation(location: LocationEntity): Long = dao.insert(location)
    suspend fun deleteLocation(location: LocationEntity): Int = dao.delete(location)

    // Sort using the first location as a reference; compute Euclidean distances.
    fun sortLocations(list: List<LocationEntity>, ascending: Boolean): List<LocationEntity> {
        if (list.isEmpty()) return list
        val reference = list.first()
        val sorted = list.drop(1).sortedBy {
            val dLat = it.latitude - reference.latitude
            val dLng = it.longitude - reference.longitude
            dLat * dLat + dLng * dLng
        }
        return if (ascending) listOf(reference) + sorted else listOf(reference) + sorted.reversed()
    }
}
