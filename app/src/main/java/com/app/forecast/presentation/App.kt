package com.app.forecast.presentation

import android.app.Application
import com.app.forecast.BuildConfig
import com.app.forecast.presentation.di.Injector
import com.app.forecast.presentation.di.forecast.ForecastSubComponent
import com.app.forecast.presentation.di.core.*
import com.app.forecast.presentation.di.detailFavorite.DetailFavoriteSubComponent
import com.app.forecast.presentation.di.favorite.FavoriteSubComponent

class App : Application(), Injector {
private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .netModule(NetModule(BuildConfig.BASE_URL))
            .remoteDataModule(RemoteDataModule(BuildConfig.API_KEY))
            .build()

    }

    override fun createForecastSubComponent(): ForecastSubComponent {
        return appComponent.forecastSubComponent().create()
    }

    override fun createFavoriteSubComponent(): FavoriteSubComponent {
       return appComponent.favoriteSubComponent().create()
    }

    override fun createDetailFavoriteSubComponent(): DetailFavoriteSubComponent {
        return appComponent.detailFavoriteSubComponent().create()
    }

}