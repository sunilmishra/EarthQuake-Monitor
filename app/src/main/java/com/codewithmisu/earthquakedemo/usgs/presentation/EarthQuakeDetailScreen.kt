package com.codewithmisu.earthquakedemo.usgs.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MonitorHeart
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.codewithmisu.earthquakedemo.components.AppBar
import com.codewithmisu.earthquakedemo.components.ErrorView
import com.codewithmisu.earthquakedemo.components.LoadingView
import com.codewithmisu.earthquakedemo.components.QuakeMapView
import com.codewithmisu.earthquakedemo.core.UiState
import com.codewithmisu.earthquakedemo.core.roundUpTo2Decimals
import com.codewithmisu.earthquakedemo.usgs.domain.Feature

@Composable
fun EarthQuakeDetailScreen(
    id: String,
    viewModel: EarthQuakeDetailViewModel,
    onBackPressed: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState) {
        viewModel.loadEarthQuakeFeature(id)
    }

    Scaffold(
        topBar = {
            AppBar(
                title = "EarthQuake Details",
                onBackPressed = onBackPressed
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            when (uiState) {
                UiState.Idle -> Unit
                UiState.Loading -> {
                    LoadingView()
                }

                is UiState.Failure -> {
                    ErrorView((uiState as UiState.Failure).message)
                }

                is UiState.Success<Feature> -> {
                    val feature = (uiState as UiState.Success<Feature>).content
                    Column {
                        feature.properties?.let {
                            Text(
                                feature.properties.title,
                                fontWeight = FontWeight.W700,
                                fontSize = 20.sp
                            )
                            Spacer(modifier = Modifier.size(24.dp))
                            Row {
                                Icon(Icons.Default.Place, contentDescription = "Place")
                                Text(feature.properties.place, fontWeight = FontWeight.W600)
                            }
                            Spacer(modifier = Modifier.size(24.dp))
                            Row {
                                Icon(Icons.Default.MonitorHeart, contentDescription = "Quake")
                                Text(
                                    feature.properties.mag!!.roundUpTo2Decimals().toString(),
                                    fontWeight = FontWeight.W700,
                                    fontSize = 24.sp,
                                    modifier = Modifier.padding(horizontal = 12.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.size(32.dp))
                        feature.geometry?.let {
                            Text(
                                it.toString(),
                                fontWeight = FontWeight.W600
                            )

                            /**
                             * To display Google Maps, an API key from the Google Cloud Console is required.
                             * Billing must be enabled to obtain the key.
                             * Add the following meta-data in the AndroidManifest.xml to render the map:
                             * <meta-data
                             *     android:name="com.google.android.geo.API_KEY"
                             *     android:value="YOUR_API_KEY"/>
                             */
//                            QuakeMapView(
//                                lat = it.coordinates!![0],
//                                lng = it.coordinates[1],
//                                place = feature.properties!!.place
//                            )
                        }
                    }
                }
            }
        }
    }
}