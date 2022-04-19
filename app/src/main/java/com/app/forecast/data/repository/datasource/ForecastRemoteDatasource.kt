package com.app.forecast.data.repository.datasource

import com.app.forecast.data.model.ForecastData
import com.app.forecast.data.model.request.ForecastRequest
import retrofit2.Response

interface ForecastRemoteDatasource {
   suspend fun getForecast(request: ForecastRequest): Response<ForecastData>
}