package com.app.forecast.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.app.forecast.domain.usecase.CityUseCase
import com.app.forecast.domain.usecase.GetForecastUseCase


class FavoriteViewModel(
    private val getForecastUseCase: GetForecastUseCase,
    private val cityUseCase: CityUseCase
): ViewModel() {

    fun getFavorite() = liveData {
        val favoriteList = cityUseCase.getCity()
        emit(favoriteList)
    }

}





