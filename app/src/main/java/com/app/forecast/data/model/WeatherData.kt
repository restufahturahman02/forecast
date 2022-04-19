package com.app.forecast.data.model


import com.google.gson.annotations.SerializedName

data class WeatherData(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("main")
    var main: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("icon")
    var icon: String? = null
)