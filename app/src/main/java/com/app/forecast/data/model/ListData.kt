package com.app.forecast.data.model


import com.google.gson.annotations.SerializedName

data class ListData(
    @SerializedName("dt_txt")
    val dtTxt: String? = null,
    @SerializedName("main")
    var main: MainData? = null,
    @SerializedName("weather")
    var weather: List<WeatherData>? = null
)