package com.app.forecast.presentation.forecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.forecast.R
import com.app.forecast.data.model.ListData
import com.app.forecast.databinding.ListItemBinding


class ForecastAdapter():RecyclerView.Adapter<MyViewHolder>() {
    private val weatherList = ArrayList<ListData>()


    fun setList(forecastData:List<ListData>){
        weatherList.clear()
        weatherList.addAll(forecastData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ListItemBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.list_item,
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return weatherList.size;
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       holder.bind(weatherList[position])
    }
}



class MyViewHolder(val binding: ListItemBinding):
RecyclerView.ViewHolder(binding.root){

   fun bind(weatherData:ListData){
       binding.txtDateTime.text = weatherData.dtTxt
        binding.txtcloud.text = weatherData.weather?.get(0)?.main
        binding.txtTemp.text = weatherData.main?.temp?.toInt().toString() + "°C"
   }

}