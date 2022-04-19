package com.app.forecast.presentation.di.core


import com.app.forecast.presentation.di.detailFavorite.DetailFavoriteSubComponent
import com.app.forecast.presentation.di.favorite.FavoriteSubComponent
import com.app.forecast.presentation.di.forecast.ForecastSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
AppModule::class,
NetModule::class,
DataBaseModule::class,
UseCaseModule::class,
RepositoryModule::class,
RemoteDataModule::class,
LocalDataModule::class,
CacheDataModule::class
])
interface AppComponent {

fun forecastSubComponent(): ForecastSubComponent.Factory
fun favoriteSubComponent():FavoriteSubComponent.Factory
fun detailFavoriteSubComponent():DetailFavoriteSubComponent.Factory

}