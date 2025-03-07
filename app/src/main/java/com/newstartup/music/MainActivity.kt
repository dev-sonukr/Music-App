package com.newstartup.music

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var myRecyclerView: RecyclerView
    lateinit var myAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        myRecyclerView= findViewById(R.id.RecyclerView)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData("eminem")

        retrofitData.enqueue(object : Callback<myData?> {
            override fun onResponse(call: Call<myData?>, response: Response<myData?>) {
                // Api call success then use this method to get the data
                val dataList = response.body()?.data!!
//                val textView = findViewById<TextView>(R.id.RecyclerView)
//                textView.text = dataList.toString()

                myAdapter= RecyclerViewAdapter(this@MainActivity,dataList)
                myRecyclerView.adapter=myAdapter
                myRecyclerView.layoutManager= LinearLayoutManager(this@MainActivity)
                Log.d("TAG : onResponse", "onResponse :"+response.body())
            }

            override fun onFailure(call: Call<myData?>, t: Throwable) {
                // Api call failed then use this method to get the error
                Log.d("TAG : onFailure", "onFailure :"+t.message)
            }
        })

    }
}