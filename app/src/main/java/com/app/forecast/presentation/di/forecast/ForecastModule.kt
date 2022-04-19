package com.app.forecast.presentation.di.forecast


import com.app.forecast.domain.usecase.CityUseCase
import com.app.forecast.domain.usecase.GetForecastUseCase
import com.app.forecast.presentation.forecast.ForecastViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ForecastModule {
    @ForecastScope
    @Provides
    fun provideForecastViewModelFactory(
        getForecastUseCase: GetForecastUseCase,
        cityUseCase: CityUseCase
    ): ForecastViewModelFactory {
        return ForecastViewModelFactory(
            getForecastUseCase,
            cityUseCase
        )
    }

}