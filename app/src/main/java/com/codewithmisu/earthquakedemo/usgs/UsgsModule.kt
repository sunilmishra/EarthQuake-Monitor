package com.codewithmisu.earthquakedemo.usgs

import com.codewithmisu.earthquakedemo.ApiClient
import com.codewithmisu.earthquakedemo.usgs.data.EarthQuakeRemoteSource
import com.codewithmisu.earthquakedemo.usgs.data.EarthQuakeRepositoryImpl
import com.codewithmisu.earthquakedemo.usgs.domain.EarthQuakeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * Using ViewModelComponent scope creates a new instance each time it is injected.
 * For EarthQuakeDetailViewModel, which uses the same repository,
 * this means a new instance is created and data is fetched from the network each time.
 *
 * Advantage: When the ViewModel is no longer in use, it can be garbage collected,
 * reducing the risk of memory leaks or unnecessarily holding data in memory.
 *
 * If SingletonComponent scope is used, the repository instance is shared,
 * and data can be returned from cache. The downside is that data remains in memory
 * as long as the app is running, which may not be ideal for large, enterprise apps.
 *
 * Here, I chose the enterprise app approach for demonstration.
 */

@Module
@InstallIn(ViewModelComponent::class)
object UsgsModule {

    @Provides
    fun providesUsgsRemoteSource(apiClient: ApiClient): EarthQuakeRemoteSource {
        return apiClient.create(EarthQuakeRemoteSource::class.java)
    }

    @Provides
    fun providesUsgsRepository(
        remoteSource: EarthQuakeRemoteSource
    ): EarthQuakeRepository {
        return EarthQuakeRepositoryImpl(
            remoteSource = remoteSource
        )
    }
}