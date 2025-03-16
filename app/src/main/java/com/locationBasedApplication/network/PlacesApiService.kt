package com.locationBasedApplication.network

import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesApiService {
    @GET("autocomplete/json")
    suspend fun getPlaceSuggestions(
        @Query("input") input: String,
        @Query("key") apiKey: String
    ): PlaceAutocompleteResponse
}
