package com.app.forecast.presentation.favorite

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
class FavoriteViewModelTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: FavoriteViewModel

    @Before
    fun setUp() {
      val fakeForecastRepository = FakeForecastRepository()
      val getForecastUsecase = GetForecastUseCase(fakeForecastRepository)
        val cityUsecase = CityUseCase(fakeForecastRepository)
        viewModel = FavoriteViewModel(getForecastUsecase,cityUsecase)
    }

    @Test
    fun getFavorite_returnsCurrentData(){
        val listCity = mutableListOf<CityData>()

        listCity.add(CityData(1,"Bandung","ID",1699719,25200,1641940967,1641985926))
        listCity.add(CityData(2,"Bandung2","ID2",11111,22222,1111111111,1111111111))

        var currentData = viewModel.getFavorite().getOrAwaitValue()
        assertThat(listCity).isEqualTo(currentData)

    }

}









