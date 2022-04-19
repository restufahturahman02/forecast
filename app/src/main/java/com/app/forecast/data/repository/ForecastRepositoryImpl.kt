package com.app.forecast.data.repository

import android.util.Log
import com.app.forecast.data.model.CityData
import com.app.forecast.data.model.ForecastData
import com.app.forecast.data.model.request.ForecastRequest
import com.app.forecast.data.repository.datasource.ForecastLocalDataSource
import com.app.forecast.data.repository.datasource.ForecastRemoteDatasource
import com.app.forecast.domain.repository.ForecastRepository
import java.lang.Exception

class ForecastRepositoryImpl(
    private val forecastRemoteDatasource: ForecastRemoteDatasource,
    private val forecastLocalDataSource: ForecastLocalDataSource
) : ForecastRepository {
    override suspend fun getForecast(request: ForecastRequest): ForecastData {
       return getForecastFromAPI(request = request)
    }

    override suspend fun getCity(): List<CityData> {
        val listCityData = forecastLocalDataSource.getCityFromDB()
        return listCityData
    }

    override suspend fun saveCity(cityData: CityData): String {
        val listCityData = forecastLocalDataSource.getCityFromDB()
        var message: String= ""
        var isAlready : Boolean= false

        if (listCityData.isNotEmpty()){
            listCityData.forEach { data ->
                if (data.id == cityData.id){
                    isAlready = true
                }
            }
        }

        if(isAlready){
            message = "City Already in Favorite"
        }else{
            forecastLocalDataSource.saveCityToDB(cityData)
            message = "Succesfully Add to Favorite"
        }

        return message
    }

    override suspend fun deleteCity(cityData: CityData): String {
        val listCityData = forecastLocalDataSource.getCityFromDB()
        var message: String= ""
        var isAlready : Boolean= false

        if (listCityData.isNotEmpty()){
            listCityData.forEach { data ->
                if (data.id == cityData.id){
                    isAlready = true
                }
            }
        }

        if(isAlready){
            forecastLocalDataSource.deleteCityFromDB(cityData.id!!)
            message = "Succesfully Delete from Favorite"
        }else{
            message = "City Already Deleted from Favorite"
        }

        return message
    }

    suspend fun getForecastFromAPI(request: ForecastRequest): ForecastData{
        lateinit var forecastData: ForecastData
        forecastData = ForecastData()
        try {
            val response = forecastRemoteDatasource.getForecast(request = request)
            Log.i("MyTag1", response.toString())
            Log.i("MyTag11", response.body().toString())

            val body = response.body()
            if(body!=null){
                forecastData = body
            }else{
                forecastData.message = response.message()
            }
        } catch (exception: Exception) {
            Log.i("MyTag2", exception.message.toString())
        }
        return forecastData
    }

}