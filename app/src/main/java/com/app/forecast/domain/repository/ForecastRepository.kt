package com.app.forecast.domain.repository

import com.app.forecast.data.model.CityData
import com.app.forecast.data.model.ForecastData
import com.app.forecast.data.model.request.ForecastRequest


interface ForecastRepository {
    suspend fun getForecast(request: ForecastRequest):ForecastData
    suspend fun getCity():List<CityData>
    suspend fun saveCity(cityData: CityData): String
    suspend fun deleteCity(cityData: CityData): String
}