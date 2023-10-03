package com.example.lab5_weatherapi

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private lateinit var etCity: EditText
    private lateinit var etCountry: EditText
    private lateinit var tvResult: TextView
    private val url = "http://api.openweathermap.org/data/2.5/weather"
    private val appid = "e53301e27efa0b66d05045d91b2742d3"
    private val df = DecimalFormat("#.##")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etCity = findViewById(R.id.etCity)
        etCountry = findViewById(R.id.etCountry)
        tvResult = findViewById(R.id.tvResult)
    }

    fun getWeatherDetails(view: View) {
        var tempUrl = ""
        val city = etCity.text.toString().trim()
        val country = etCountry.text.toString().trim()

        if (city.isEmpty()) {
            tvResult.text = "City field cannot be empty!"
        } else {
            tempUrl = if (country.isEmpty()) {
                "$url?q=$city&appid=$appid"
            } else {
                "$url?q=$city,$country&appid=$appid"
            }

            val stringRequest = StringRequest(Request.Method.POST, tempUrl,
                { response ->
                    Log.d("response", response)
                },
                { error ->
                    Toast.makeText(applicationContext, error.message, Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}

