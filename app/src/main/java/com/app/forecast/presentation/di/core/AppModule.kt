package com.app.forecast.presentation.di.core

import android.content.Context
import com.app.forecast.presentation.di.detailFavorite.DetailFavoriteSubComponent
import com.app.forecast.presentation.di.favorite.FavoriteSubComponent
import com.app.forecast.presentation.di.forecast.ForecastSubComponent

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [ForecastSubComponent::class,FavoriteSubComponent::class,DetailFavoriteSubComponent::class])
class AppModule(private val context: Context) {

 @Singleton
 @Provides
 fun provideApplicationContext():Context{
     return context.applicationContext
 }





}