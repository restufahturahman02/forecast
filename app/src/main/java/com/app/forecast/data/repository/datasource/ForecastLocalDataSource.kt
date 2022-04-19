package com.app.forecast.data.repository.datasource

import com.app.forecast.data.model.CityData


interface ForecastLocalDataSource {
  suspend fun getCityFromDB():List<CityData>
  suspend fun saveCityToDB(cityData:CityData)
  suspend fun deleteCityFromDB(id: Int)
}