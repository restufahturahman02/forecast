package com.app.forecast.presentation.di.forecast


import com.app.forecast.presentation.forecast.MainActivity
import dagger.Subcomponent

@ForecastScope
@Subcomponent(modules = [ForecastModule::class])
interface ForecastSubComponent {
    fun inject(mainActivity: MainActivity)

    @Subcomponent.Factory
    interface Factory{
        fun create(): ForecastSubComponent
    }

}

