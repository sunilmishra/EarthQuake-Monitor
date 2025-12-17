package com.codewithmisu.earthquakedemo.usgs.data

import com.codewithmisu.earthquakedemo.usgs.domain.EarthQuakeRepository
import com.codewithmisu.earthquakedemo.usgs.domain.Earthquake

class EarthQuakeRepositoryImpl(
//    val localSource: EarthQuakeLocalSource,
    val remoteSource: EarthQuakeRemoteSource
) : EarthQuakeRepository {

    /** Temporary caching using Room (to be fully implemented later using [EarthQuakeLocalSource])
     * 1. First, fetch data from Room and return it.
     * 2. Network request is made only on a forced fetch, updating the database afterwards.
     * 3. Kotlin Flow automatically observes changes in Room tables and updates the UI via UiState.
     * Note: Due to time constraints (3 hours), this is a shortcut implementation.
     */
    private var cache: Earthquake? = null

    override suspend fun getEarthQuakeInfo(forceRefresh: Boolean): Earthquake {
        if (cache == null || forceRefresh) {
            val response = remoteSource.fetchEarthQuake()
            return response.toDomain()
        }
        return cache!!
    }
}