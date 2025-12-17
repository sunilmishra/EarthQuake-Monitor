package com.codewithmisu.earthquakedemo

import com.codewithmisu.earthquakedemo.usgs.UsgsModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [UsgsModule::class])
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesApiClient(): ApiClient {
        return ApiClient("https://earthquake.usgs.gov")
    }
}
