package com.app.forecast.domain.usecase

import com.app.forecast.data.model.ForecastData
import com.app.forecast.data.model.request.ForecastRequest
import com.app.forecast.domain.repository.ForecastRepository


class GetForecastUseCase(private val forecastRepository: ForecastRepository) {
  suspend fun execute(request : ForecastRequest):ForecastData = forecastRepository.getForecast(request = request)
}