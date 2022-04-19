package com.app.forecast.presentation.di.core

import android.content.Context
import androidx.room.Room
import com.app.forecast.data.db.CityDao
import com.app.forecast.data.db.ForecastDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {
    @Singleton
    @Provides
    fun provideForecastDataBase(context: Context): ForecastDatabase {
     return Room.databaseBuilder(context,ForecastDatabase::class.java,"forecast")
         .fallbackToDestructiveMigration()
         .build()
    }
    @Singleton
    @Provides
    fun provideCityDao(forecastDatabase: ForecastDatabase): CityDao {
        return forecastDatabase.cityDao()
    }

}