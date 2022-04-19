package com.app.forecast.presentation.di.favorite


import com.app.forecast.presentation.favorite.FavoriteActivity
import dagger.Subcomponent

@FavoriteScope
@Subcomponent(modules = [FavoriteModule::class])
interface FavoriteSubComponent {
    fun inject(favoriteActivity: FavoriteActivity)

    @Subcomponent.Factory
    interface Factory{
        fun create(): FavoriteSubComponent
    }

}

