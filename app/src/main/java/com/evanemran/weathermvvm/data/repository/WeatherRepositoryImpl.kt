package com.evanemran.weathermvvm.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.evanemran.weathermvvm.data.mappers.toWeatherInfo
import com.evanemran.weathermvvm.data.remote.WeatherApi
import com.evanemran.weathermvvm.domain.repository.WeatherRepository
import com.evanemran.weathermvvm.domain.util.Resource
import com.evanemran.weathermvvm.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
): WeatherRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeatherData(
                    lat = lat, long = long
                ).toWeatherInfo()
            )
        }
        catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred!")
        }
    }

}