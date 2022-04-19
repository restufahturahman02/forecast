package com.app.forecast.presentation.di.detailFavorite


import com.app.forecast.presentation.detailFavorite.DetailFavoriteActivity
import dagger.Subcomponent

@DetailFavoriteScope
@Subcomponent(modules = [DetailFavoriteModule::class])
interface DetailFavoriteSubComponent {
    fun inject(detailFavoriteActivity: DetailFavoriteActivity)

    @Subcomponent.Factory
    interface Factory{
        fun create(): DetailFavoriteSubComponent
    }

}

