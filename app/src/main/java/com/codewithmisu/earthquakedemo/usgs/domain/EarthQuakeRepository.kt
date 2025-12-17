package com.codewithmisu.earthquakedemo.usgs.domain

interface EarthQuakeRepository {

    suspend fun getEarthQuakeInfo(forceRefresh: Boolean = false): Earthquake
}