package com.app.forecast.presentation.forecast

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
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.forecast.R
import com.app.forecast.databinding.ActivityMainBinding
import com.app.forecast.presentation.di.Injector
import javax.inject.Inject

import com.app.forecast.data.model.CityData
import com.app.forecast.presentation.favorite.FavoriteActivity


class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: ForecastViewModelFactory
    private lateinit var forecastViewModel: ForecastViewModel
    private lateinit var binding:ActivityMainBinding
    private lateinit var adapter: ForecastAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        (application as Injector).createForecastSubComponent()
            .inject(this)

        forecastViewModel= ViewModelProvider(this,factory)
            .get(ForecastViewModel::class.java)

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
        adapter = ForecastAdapter()
        binding.forecastRecyleView.adapter = adapter

        binding.searhView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                displayForecast(queryCity = query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        binding.imgFavorite.setOnClickListener {
            val intent = Intent(this,FavoriteActivity::class.java)
            startActivity(intent)
        }
    }

    private fun displayForecast(queryCity : String){
        binding.forecastProgressBar.visibility = View.VISIBLE

        if (checkForInternet(this)){
            val responseLiveData = forecastViewModel.getForecast(city = queryCity, cnt = 24, units = "metric")
            responseLiveData.observe(this, Observer {
                if(it!=null){
                    if (it.cod == "200"){
                        val data = it

                        adapter.setList(it.list!!)
                        adapter.notifyDataSetChanged()

                        binding.forecastProgressBar.visibility = View.GONE
                        binding.txtDefault.visibility = View.GONE
                        binding.txtCloud.visibility = View.VISIBLE
                        binding.txtCity.visibility = View.VISIBLE
                        binding.txtTemp.visibility = View.VISIBLE
                        binding.bottomLinear.visibility = View.VISIBLE
                        binding.txtCloud.text = data.list?.get(0)?.weather?.get(0)?.main
                        binding.txtCity.text = data.city?.name
                        binding.txtTemp.text = data.list?.get(0)?.main?.temp?.toInt().toString() + "°C"


                        binding.myButton.setOnClickListener{
                            saveCity(data.city!!)
                        }
                    }else{
                        binding.forecastProgressBar.visibility = View.GONE
                        binding.txtCloud.visibility = View.GONE
                        binding.txtCity.visibility = View.GONE
                        binding.txtTemp.visibility = View.GONE
                        binding.bottomLinear.visibility = View.GONE
                        binding.txtDefault.visibility = View.VISIBLE
                        binding.txtDefault.text = it.message
                    }
                }else{
                    binding.forecastProgressBar.visibility = View.GONE
                    Toast.makeText(applicationContext,"Something went wrong", Toast.LENGTH_LONG).show()
                }
            })
        }else{
            binding.forecastProgressBar.visibility = View.GONE
            showDialog()
        }
    }

    private fun saveCity(data: CityData){
        val responseLiveData = forecastViewModel.saveCity(cityData = data)
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
                displayForecast(queryCity = binding.searhView.query.toString())
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })

        val alert = dialogBuilder.create()
        alert.setTitle("No Internet Connection")
        alert.show()
    }

}
