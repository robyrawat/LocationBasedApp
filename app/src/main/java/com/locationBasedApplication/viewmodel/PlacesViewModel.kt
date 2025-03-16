package com.locationBasedApplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.locationBasedApplication.network.PlaceAutocompleteResponse
import com.locationBasedApplication.network.PlacesApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaceViewModel @Inject constructor(
    private val placesApi: PlacesApiService
) : ViewModel() {
    private val _suggestions = MutableStateFlow<List<String>>(emptyList())
    val suggestions: StateFlow<List<String>> = _suggestions

    fun searchPlaces(query: String, apiKey: String) {
        viewModelScope.launch {
            try {
                val response: PlaceAutocompleteResponse = placesApi.getPlaceSuggestions(query, apiKey)
                _suggestions.value = if (response.status == "OK") {
                    response.predictions.map { it.description }
                } else {
                    emptyList()
                }
            } catch (e: Exception) {
                _suggestions.value = emptyList()
            }
        }
    }
}
