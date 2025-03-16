package com.locationBasedApplication.network

data class PlaceAutocompleteResponse(
    val predictions: List<Prediction>,
    val status: String
)
