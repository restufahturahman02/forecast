package com.app.forecast.data.api

import com.app.forecast.data.model.ForecastData
import retrofit2.http.GET
import retrofit2.http.Query


interface ForecastService {
    @GET("forecast")
    suspend fun getForecast(
        @Query("q") city: String,
        @Query("cnt") cnt: Int,
        @Query("units") units: String,
        @Query("appid") apiKey: String
    ): retrofit2.Response<ForecastData>
}