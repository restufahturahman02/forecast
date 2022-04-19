package com.app.forecast.presentation.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.forecast.R
import com.app.forecast.data.model.CityData
import com.app.forecast.presentation.di.Injector
import javax.inject.Inject
import com.app.forecast.databinding.ActivityFavoriteBinding
import com.app.forecast.presentation.detailFavorite.DetailFavoriteActivity
import com.app.forecast.presentation.forecast.MainActivity


class FavoriteActivity : AppCompatActivity(), RecyclerViewClickListener {
    @Inject
    lateinit var factory: FavoriteViewModelFactory
    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var binding:ActivityFavoriteBinding
    private lateinit var adapter: FavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_favorite)
        (application as Injector).createFavoriteSubComponent()
            .inject(this)

        favoriteViewModel= ViewModelProvider(this,factory)
            .get(FavoriteViewModel::class.java)

        init()
    }

    private fun init(){
        binding.favoriteRecyleView.layoutManager = LinearLayoutManager(this)
        adapter = FavoriteAdapter()
        binding.favoriteRecyleView.adapter = adapter
        adapter.listener = this


        binding.btnBack.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        displayFavorite()
    }

    private fun displayFavorite(){
        binding.progressBar.visibility = View.VISIBLE
        val responseLiveData = favoriteViewModel.getFavorite()
        responseLiveData.observe(this, Observer {
          if(it.isNotEmpty()){
              adapter.setList(it)
              adapter.notifyDataSetChanged()
              binding.progressBar.visibility = View.GONE
          }else{
              binding.progressBar.visibility = View.GONE
              binding.txtDefault.visibility = View.VISIBLE
          }
        })
    }

    override fun onItemClicked(view: View, favoriteData: CityData) {
        val intent = Intent(this,DetailFavoriteActivity::class.java)
        intent.putExtra("city_name", favoriteData.name)
        startActivity(intent)
    }

}
