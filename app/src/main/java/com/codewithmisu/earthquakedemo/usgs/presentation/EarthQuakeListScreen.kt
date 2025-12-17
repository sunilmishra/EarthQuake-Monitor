package com.codewithmisu.earthquakedemo.usgs.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.codewithmisu.earthquakedemo.components.AppBar
import com.codewithmisu.earthquakedemo.components.ErrorView
import com.codewithmisu.earthquakedemo.components.LoadingView
import com.codewithmisu.earthquakedemo.core.UiState
import com.codewithmisu.earthquakedemo.core.roundUpTo2Decimals
import com.codewithmisu.earthquakedemo.core.toFormattedDateTime
import com.codewithmisu.earthquakedemo.usgs.domain.Earthquake
import com.codewithmisu.earthquakedemo.usgs.domain.Feature

@Composable
fun EarthQuakeListScreen(
    viewModel: EarthQuakeListViewModel,
    onItemClick: (String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val pullToRefreshState by viewModel.pullToRefreshState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            AppBar(
                title = "EarthQuake",
                actions = {
                    IconButton(
                        onClick = {
                            viewModel.loadEarthQuakeData(true)
                        }
                    ) {
                        Icon(
                            Icons.Default.Refresh,
                            contentDescription = "Refresh",
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            when (uiState) {
                UiState.Idle -> Unit
                UiState.Loading -> {
                    LoadingView()
                }

                is UiState.Failure -> {
                    ErrorView((uiState as UiState.Failure).message)
                }

                is UiState.Success<Earthquake> -> {
                    val earthquake = (uiState as UiState.Success<Earthquake>).content
                    EarthQuakeListView(
                        features = earthquake.features,
                        onItemClick = {
                            onItemClick(it)
                        },
                        onRefresh = {
                            viewModel.loadEarthQuakeData(forceRefresh = true)
                        },
                        isRefreshing = pullToRefreshState
                    )
                }
            }
        }
    }
}

@Composable
private fun EarthQuakeListView(
    features: List<Feature>,
    onItemClick: (id: String) -> Unit,
    onRefresh: () -> Unit,
    isRefreshing: Boolean
) {
    PullToRefreshBox(
        onRefresh = onRefresh,
        isRefreshing = isRefreshing,
    ) {
        LazyColumn {
            itemsIndexed(features) { index, item ->
                ListItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .clickable {
                            onItemClick(item.id)
                        },
                    headlineContent = {
                        val properties = item.properties
                        properties?.let {
                            Text(it.title.uppercase(), fontWeight = FontWeight.W600)
                        }
                    },
                    supportingContent = {
                        val properties = item.properties
                        properties?.let {
                            Column {
                                Text(it.time!!.toFormattedDateTime())
                                Text(
                                    "Place: ${it.place}",
                                    fontWeight = FontWeight.W500
                                )
                                it.mag?.let { mag ->
                                    Text(
                                        "Magnitude: ${mag.roundUpTo2Decimals()}",
                                        color = if (mag > 9.0) Color(0xFF7F0000) else Color(
                                            0xFF006400
                                        ),
                                        fontWeight = FontWeight.W800,
                                        fontSize = 24.sp
                                    )
                                }
                            }
                        }
                    },
                    trailingContent = {
                        Icon(
                            Icons.Default.ChevronRight,
                            contentDescription = "Right Arrow",
                        )
                    }
                )
                if (index != features.lastIndex) {
                    HorizontalDivider()
                }
            }
        }
    }
}