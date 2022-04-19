package com.app.forecast.presentation.detailFavorite

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.forecast.R
import com.app.forecast.presentation.di.Injector
import javax.inject.Inject

import com.app.forecast.data.model.CityData
import com.app.forecast.databinding.ActivityDetailFavoriteBinding
import com.app.forecast.presentation.favorite.FavoriteActivity


class DetailFavoriteActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: DetailFavoriteViewModelFactory
    private lateinit var detailFavoriteViewModel: DetailFavoriteViewModel
    private lateinit var binding:ActivityDetailFavoriteBinding
    private lateinit var adapter: DetailFavoriteAdapter

    private lateinit var cityName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_detail_favorite)
        (application as Injector).createDetailFavoriteSubComponent()
            .inject(this)

        detailFavoriteViewModel= ViewModelProvider(this,factory)
            .get(DetailFavoriteViewModel::class.java)

        cityName = intent.getStringExtra("city_name")

        init()
    }

    private fun init(){

        binding.forecastRecyleView.layoutManager = LinearLayoutManager(this)
        binding.forecastRecyleView.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
        adapter = DetailFavoriteAdapter()
        binding.forecastRecyleView.adapter = adapter

        binding.btnBack.setOnClickListener {
            val intent = Intent(this,FavoriteActivity::class.java)
            startActivity(intent)
        }

        displayForecast(queryCity = cityName)
    }

    private fun displayForecast(queryCity : String){
        binding.detailFavoriteprogressBar.visibility = View.VISIBLE

        if (checkForInternet(this)){
            val responseLiveData = detailFavoriteViewModel.getForecast(city = queryCity, cnt = 24, units = "metric")
            responseLiveData.observe(this, Observer {
                if(it!=null){
                    if (it.cod == "200"){
                        val data = it

                        adapter.setList(it.list!!)
                        adapter.notifyDataSetChanged()

                        binding.detailFavoriteprogressBar.visibility = View.GONE
                        binding.txtDefault.visibility = View.GONE
                        binding.txtCloud.visibility = View.VISIBLE
                        binding.txtCity.visibility = View.VISIBLE
                        binding.txtTemp.visibility = View.VISIBLE
                        binding.bottomLinear.visibility = View.VISIBLE
                        binding.txtCloud.text = data.list?.get(0)?.weather?.get(0)?.main
                        binding.txtCity.text = data.city?.name
                        binding.txtTemp.text = data.list?.get(0)?.main?.temp?.toInt().toString() + "°C"


                        binding.myButton.setOnClickListener{
                            deleteCity(data.city!!)
                        }
                    }else{
                        binding.detailFavoriteprogressBar.visibility = View.GONE
                        binding.txtCloud.visibility = View.GONE
                        binding.txtCity.visibility = View.GONE
                        binding.txtTemp.visibility = View.GONE
                        binding.bottomLinear.visibility = View.GONE
                        binding.txtDefault.visibility = View.VISIBLE
                        binding.txtDefault.text = it.message
                    }
                }else{
                    binding.detailFavoriteprogressBar.visibility = View.GONE
                    Toast.makeText(applicationContext,"Something went wrong", Toast.LENGTH_LONG).show()
                }
            })
        }else{
            binding.detailFavoriteprogressBar.visibility = View.GONE
            showDialog()
        }
    }

    private fun deleteCity(data: CityData){
        val responseLiveData = detailFavoriteViewModel.deleteCity(cityData = data)
        responseLiveData.observe(this, Observer {
            Toast.makeText(applicationContext,it, Toast.LENGTH_LONG).show()
        })
    }

    private fun checkForInternet(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION") val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

    private fun showDialog(){
        val dialogBuilder = AlertDialog.Builder(this)

        dialogBuilder.setMessage("Try connect to internet and try again!")
            .setCancelable(false)
            .setPositiveButton("Retry", DialogInterface.OnClickListener {
                    dialog, id -> dialog.dismiss()
                displayForecast(queryCity = cityName)
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })

        val alert = dialogBuilder.create()
        alert.setTitle("No Internet Connection")
        alert.show()
    }

}
