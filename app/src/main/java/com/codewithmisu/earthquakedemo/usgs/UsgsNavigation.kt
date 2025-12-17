package com.codewithmisu.earthquakedemo.usgs

import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.codewithmisu.earthquakedemo.usgs.presentation.EarthQuakeDetailScreen
import com.codewithmisu.earthquakedemo.usgs.presentation.EarthQuakeDetailViewModel
import com.codewithmisu.earthquakedemo.usgs.presentation.EarthQuakeListScreen
import com.codewithmisu.earthquakedemo.usgs.presentation.EarthQuakeListViewModel
import kotlinx.serialization.Serializable

@Serializable
data object EarthQuakeListRoute : NavKey

@Serializable
data class EarthQuakeDetailRoute(val id: String) : NavKey

fun EntryProviderScope<NavKey>.usgsNavigator(
    backStack: NavBackStack<NavKey>,
) {
    entry<EarthQuakeListRoute> {
        val earthQuakeListViewModel: EarthQuakeListViewModel = hiltViewModel()
        EarthQuakeListScreen(
            viewModel = earthQuakeListViewModel,
            onItemClick = {
                backStack.add(EarthQuakeDetailRoute(it))
            }
        )
    }

    entry<EarthQuakeDetailRoute> {
        val earthQuakeDetailViewModel: EarthQuakeDetailViewModel = hiltViewModel()
        EarthQuakeDetailScreen(
            id = it.id,
            viewModel = earthQuakeDetailViewModel,
            onBackPressed = {
                backStack.removeLastOrNull()
            }
        )
    }
}