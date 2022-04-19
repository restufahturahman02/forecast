package com.app.forecast.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "city_data")
data class CityData(
    @PrimaryKey
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("country")
    val country: String? = null,
    @SerializedName("population")
    val population: Int? = null,
    @SerializedName("timezone")
    val timezone: Int? = null,
    @SerializedName("sunrise")
    val sunrise: Int? = null,
    @SerializedName("sunset")
    val sunset: Int? = null
)