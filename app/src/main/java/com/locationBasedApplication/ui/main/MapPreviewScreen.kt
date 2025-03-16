package com.locationBasedApplication.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.rememberMarkerState
import com.locationBasedApplication.viewmodel.LocationViewModel

@Composable
fun MapPreviewScreen(
    onNavigateBack: () -> Unit,
    viewModel: LocationViewModel = hiltViewModel()
) {
    val locations by viewModel.locations.collectAsState()
    // Create a camera position state with a default position.
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(0.0, 0.0), 1f)
    }

    // Animate camera to cover all locations by calculating bounds.
    LaunchedEffect(locations) {
        if (locations.isNotEmpty()) {
            val builder = LatLngBounds.builder()
            locations.forEach { location ->
                builder.include(LatLng(location.latitude, location.longitude))
            }
            val bounds = builder.build()
            cameraPositionState.animate(
                update = CameraUpdateFactory.newLatLngBounds(bounds, 50)
            )
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            cameraPositionState = cameraPositionState,
            modifier = Modifier.fillMaxSize()
        ) {
            // Place a marker for each location.
            locations.forEach { location ->
                Marker(
                    state = rememberMarkerState(
                        position = LatLng(location.latitude, location.longitude)
                    ),
                    title = location.name
                )
            }
            // Draw a polyline connecting all locations if there is more than one.
            if (locations.size > 1) {
                Polyline(
                    points = locations.map { LatLng(it.latitude, it.longitude) },
                    color = Color.Blue,
                    width = 5f
                )
            }
        }
        // A Back button overlaid on the map.
        Button(
            onClick = onNavigateBack,
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Back")
        }
    }
}