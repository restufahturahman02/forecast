package com.app.forecast.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.forecast.data.model.CityData

@Dao
interface CityDao {

@Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun saveCity(forecastData : CityData)

@Query("DELETE FROM city_data WHERE id = :id")
suspend fun deleteCity(id:Int)

@Query("SELECT * FROM city_data")
suspend fun getAllCity():List<CityData>
}