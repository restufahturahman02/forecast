package com.app.forecast.presentation.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.forecast.R
import com.app.forecast.data.model.CityData
import com.app.forecast.databinding.ListItemFavoriteBinding


class FavoriteAdapter():RecyclerView.Adapter<MyViewHolder>() {
    private val favoriteList = ArrayList<CityData>()
    var listener: RecyclerViewClickListener? = null


    fun setList(favoriteData:List<CityData>){
        favoriteList.clear()
        favoriteList.addAll(favoriteData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ListItemFavoriteBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.list_item_favorite,
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return favoriteList.size;
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.txtCityName.text = favoriteList[position].name
        holder.binding.itemCity.setOnClickListener {
            listener?.onItemClicked(it, favoriteData = favoriteList[position])
        }
        holder.binding
    }
}



class MyViewHolder(val binding: ListItemFavoriteBinding):
RecyclerView.ViewHolder(binding.root){
}