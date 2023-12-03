package com.evanemran.weathermvvm.di

import com.evanemran.weathermvvm.data.location.DefaultLocationTracker
import com.evanemran.weathermvvm.data.repository.WeatherRepositoryImpl
import com.evanemran.weathermvvm.domain.location.LocationTracker
import com.evanemran.weathermvvm.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(
        weatherRepositoryImpl: WeatherRepositoryImpl
    ): WeatherRepository
}