package com.app.forecast.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.forecast.data.model.CityData


@Database(entities = [CityData::class],
version = 2,
exportSchema = false
)
abstract class ForecastDatabase : RoomDatabase(){
abstract fun cityDao(): CityDao

}