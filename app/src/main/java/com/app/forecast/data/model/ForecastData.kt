package com.app.forecast.data.model


import com.google.gson.annotations.SerializedName

data class ForecastData(
    @SerializedName("cod")
    val cod: String? = null,
    @SerializedName("message")
    var message: String? = null,
    @SerializedName("cnt")
    val cnt: Int? = null,
    @SerializedName("list")
    val list: List<ListData>? = null,
    @SerializedName("city")
    val city: CityData? = null

)