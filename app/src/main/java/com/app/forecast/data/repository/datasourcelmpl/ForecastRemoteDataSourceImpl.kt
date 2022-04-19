package com.app.forecast.data.repository.datasourcelmpl

import com.app.forecast.data.api.ForecastService
import com.app.forecast.data.model.ForecastData
import com.app.forecast.data.model.request.ForecastRequest
import com.app.forecast.data.repository.datasource.ForecastRemoteDatasource
import retrofit2.Response

class ForecastRemoteDataSourceImpl(
    private val forecastService: ForecastService,
    private val apiKey:String
): ForecastRemoteDatasource {
    override suspend fun getForecast(request: ForecastRequest): Response<ForecastData> = forecastService.getForecast(request.city, request.cnt, request.units, apiKey)
}

