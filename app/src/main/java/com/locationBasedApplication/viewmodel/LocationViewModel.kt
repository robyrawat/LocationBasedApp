package com.locationBasedApplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.locationBasedApplication.data.LocationEntity
import com.locationBasedApplication.repository.LocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val repository: LocationRepository
) : ViewModel() {
    val locations: StateFlow<List<LocationEntity>> =
        repository.locations.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun addLocation(location: LocationEntity) {
        viewModelScope.launch { repository.addLocation(location) }
    }

    fun deleteLocation(location: LocationEntity) {
        viewModelScope.launch { repository.deleteLocation(location) }
    }
}
