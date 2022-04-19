package com.app.forecast.data.model.request


data class ForecastRequest(
    var city: String,
    var cnt: Int,
    var units: String
)