package com.app.forecast


import com.app.forecast.data.model.*
import com.app.forecast.data.model.request.ForecastRequest
import com.app.forecast.domain.repository.ForecastRepository

class FakeForecastRepository : ForecastRepository {
    private var city = CityData()
    private var forecast = ForecastData()
    private var main = MainData()
    private val listData = mutableListOf<ListData>()
    private val weather = mutableListOf<WeatherData>()
    private val listCity = mutableListOf<CityData>()

    init {
        main = MainData(23.85,23.85,23.85)
        city = CityData(1,"Bandung","ID",1699719,25200,1641940967,1641985926)
        weather.add(WeatherData(500,"Rain","light rain","10d"))
        listData.add(ListData("2022-01-12 09:00:00",main,weather))

        forecast = ForecastData("200", "Success", 24, listData, city)

        listCity.add(CityData(1,"Bandung","ID",1699719,25200,1641940967,1641985926))
        listCity.add(CityData(2,"Bandung2","ID2",11111,22222,1111111111,1111111111))
    }

    override suspend fun getForecast(request: ForecastRequest): ForecastData {
        return forecast
    }

    override suspend fun getCity(): List<CityData> {
        return listCity
    }

    override suspend fun saveCity(cityData: CityData): String {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCity(cityData: CityData): String {
        TODO("Not yet implemented")
    }


}