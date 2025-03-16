package com.locationBasedApplication.ui.main
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.locationBasedApplication.viewmodel.LocationViewModel
import com.ravi.locationBasedApplication.R
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationListScreen(
    onAddClicked: () -> Unit,
    onMapClicked: () -> Unit,
    viewModel: LocationViewModel = hiltViewModel()
) {
    val locations by viewModel.locations.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Location App") },
                navigationIcon = {
                    IconButton(onClick = { /* handle click */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.location),
                            contentDescription = "App Icon"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary // This will be your orange if set properly
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Top buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = onAddClicked) { Text("Add Location") }
                Button(onClick = onMapClicked) { Text("View Map") }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // List of locations with spacing between cards
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(locations) { location ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { /* Optionally add edit functionality */ },
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = location.name,
                                    style = androidx.compose.material3.MaterialTheme.typography.titleMedium
                                )
                                Text(
                                    text = "Lat: ${location.latitude}, Lng: ${location.longitude}",
                                    style = androidx.compose.material3.MaterialTheme.typography.bodyMedium
                                )
                            }
                            IconButton(onClick = { viewModel.deleteLocation(location) }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_delete_forever_24),
                                    contentDescription = "Delete Location",
                                    tint = Color.Red
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}