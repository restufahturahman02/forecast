package com.app.forecast.data.model


import com.google.gson.annotations.SerializedName

data class MainData(
    @SerializedName("temp")
    val temp: Double? = null,
    @SerializedName("temp_min")
    var temp_min: Double? = null,
    @SerializedName("temp_max")
    var temp_max: Double? = null
)