package com.app.forecast.presentation.detailFavorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.forecast.domain.usecase.CityUseCase
import com.app.forecast.domain.usecase.GetForecastUseCase


class DetailFavoriteViewModelFactory(
    private val getForecastUseCase: GetForecastUseCase,
    private val cityUseCase: CityUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailFavoriteViewModel(getForecastUseCase,cityUseCase) as T
    }
}

