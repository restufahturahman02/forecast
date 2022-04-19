package com.app.forecast.presentation.forecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.app.forecast.data.model.CityData
import com.app.forecast.data.model.request.ForecastRequest
import com.app.forecast.domain.usecase.CityUseCase
import com.app.forecast.domain.usecase.GetForecastUseCase


class ForecastViewModel(
    private val getForecastUseCase: GetForecastUseCase,
    private val cityUseCase: CityUseCase
): ViewModel() {

    fun getForecast(city: String, cnt: Int, units:String) = liveData {
        val forecastRequest = ForecastRequest(
            city = city,
            cnt = cnt,
            units = units
        )
        val forecastList = getForecastUseCase.execute(request = forecastRequest)
        emit(forecastList)
    }

    fun saveCity(cityData: CityData) = liveData {
        val saveCity = cityUseCase.saveCity(cityData = cityData)
        emit(saveCity)
    }
}





