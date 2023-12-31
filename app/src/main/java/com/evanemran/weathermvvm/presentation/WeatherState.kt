package com.evanemran.weathermvvm.presentation

import com.evanemran.weathermvvm.domain.weather.WeatherInfo

data class WeatherState (
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)