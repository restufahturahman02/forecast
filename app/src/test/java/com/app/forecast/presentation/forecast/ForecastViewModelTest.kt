package com.app.forecast.presentation.forecast

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.app.forecast.FakeForecastRepository
import com.app.forecast.data.model.*
import com.app.forecast.domain.usecase.CityUseCase
import com.app.forecast.domain.usecase.GetForecastUseCase
import com.app.forecast.getOrAwaitValue
import com.google.common.truth.Truth.assertThat

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ForecastViewModelTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ForecastViewModel

    @Before
    fun setUp() {
      val fakeForecastRepository = FakeForecastRepository()
      val getForecastUsecase = GetForecastUseCase(fakeForecastRepository)
        val cityUsecase = CityUseCase(fakeForecastRepository)
        viewModel = ForecastViewModel(getForecastUsecase,cityUsecase)
    }

    @Test
    fun getForrcast_returnsCurrentData(){
        var city = CityData()
        var forecast = ForecastData()
        var main = MainData()
        val listData = mutableListOf<ListData>()
        val weather = mutableListOf<WeatherData>()

        main = MainData(23.85,23.85,23.85)
        city = CityData(1,"Bandung","ID",1699719,25200,1641940967,1641985926)
        weather.add(WeatherData(500,"Rain","light rain","10d"))
        listData.add(ListData("2022-01-12 09:00:00",main,weather))
        forecast = ForecastData("200", "Success", 24, listData, city)

        var currentData = viewModel.getForecast(city = "Bandung", cnt = 24, units = "metrics").getOrAwaitValue()
        assertThat(forecast).isEqualTo(currentData)

    }

}









