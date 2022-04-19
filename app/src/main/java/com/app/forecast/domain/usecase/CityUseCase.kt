package com.app.forecast.domain.usecase

import com.app.forecast.data.model.CityData
import com.app.forecast.domain.repository.ForecastRepository


class CityUseCase(private val forecastRepository: ForecastRepository) {
  suspend fun saveCity(cityData: CityData): String = forecastRepository.saveCity(cityData = cityData)
  suspend fun getCity():List<CityData> = forecastRepository.getCity()
  suspend fun deleteCity(cityData: CityData): String = forecastRepository.deleteCity(cityData = cityData)
}