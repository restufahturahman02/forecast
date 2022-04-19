package com.app.forecast.presentation.favorite

import android.view.View
import com.app.forecast.data.model.CityData

interface RecyclerViewClickListener {
    fun onItemClicked(view: View, favoriteData: CityData)
}