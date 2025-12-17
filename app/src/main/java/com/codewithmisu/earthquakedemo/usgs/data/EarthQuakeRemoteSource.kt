package com.codewithmisu.earthquakedemo.usgs.data
import retrofit2.http.GET

interface EarthQuakeRemoteSource {

    @GET("/earthquakes/feed/v1.0/summary/all_hour.geojson")
    suspend fun fetchEarthQuake(): EarthquakeResponseDTO
}