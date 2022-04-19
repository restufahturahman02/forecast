package com.app.forecast.presentation.di.favorite


import com.app.forecast.domain.usecase.CityUseCase
import com.app.forecast.domain.usecase.GetForecastUseCase
import com.app.forecast.presentation.favorite.FavoriteViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class FavoriteModule {
    @FavoriteScope
    @Provides
    fun provideFavoriteViewModelFactory(
        getForecastUseCase: GetForecastUseCase,
        cityUseCase: CityUseCase
    ): FavoriteViewModelFactory {
        return FavoriteViewModelFactory(
            getForecastUseCase,
            cityUseCase
        )
    }

}