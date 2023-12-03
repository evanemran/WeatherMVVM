package com.evanemran.weathermvvm.domain.repository

import com.evanemran.weathermvvm.domain.util.Resource
import com.evanemran.weathermvvm.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double) : Resource<WeatherInfo>
}