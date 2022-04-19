package com.app.forecast.presentation.di

import com.app.forecast.presentation.di.detailFavorite.DetailFavoriteSubComponent
import com.app.forecast.presentation.di.favorite.FavoriteSubComponent
import com.app.forecast.presentation.di.forecast.ForecastSubComponent


interface Injector {
   fun createForecastSubComponent(): ForecastSubComponent
   fun createFavoriteSubComponent(): FavoriteSubComponent
   fun createDetailFavoriteSubComponent():DetailFavoriteSubComponent
}