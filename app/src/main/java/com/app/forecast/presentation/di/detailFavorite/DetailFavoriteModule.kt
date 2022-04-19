package com.app.forecast.presentation.di.detailFavorite


import com.app.forecast.domain.usecase.CityUseCase
import com.app.forecast.domain.usecase.GetForecastUseCase
import com.app.forecast.presentation.detailFavorite.DetailFavoriteViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class DetailFavoriteModule {
    @DetailFavoriteScope
    @Provides
    fun provideDetailFavoriteViewModelFactory(
        getForecastUseCase: GetForecastUseCase,
        cityUseCase: CityUseCase
    ): DetailFavoriteViewModelFactory {
        return DetailFavoriteViewModelFactory(
            getForecastUseCase,
            cityUseCase
        )
    }

}