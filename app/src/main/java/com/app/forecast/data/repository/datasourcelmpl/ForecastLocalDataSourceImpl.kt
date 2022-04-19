package com.app.forecast.data.repository.datasourcelmpl

import com.app.forecast.data.db.CityDao
import com.app.forecast.data.model.CityData
import com.app.forecast.data.repository.datasource.ForecastLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ForecastLocalDataSourceImpl(private val cityDao: CityDao):
    ForecastLocalDataSource {
    override suspend fun getCityFromDB(): List<CityData> {
        return cityDao.getAllCity()
    }

    override suspend fun saveCityToDB(cityData: CityData) {
        CoroutineScope(Dispatchers.IO).launch {
            cityDao.saveCity(cityData)
        }
    }

    override suspend fun deleteCityFromDB(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            cityDao.deleteCity(id)
        }
    }

}