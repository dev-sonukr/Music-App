package com.newstartup.music

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {

    @Headers(
        "x-rapidapi-key: 421e298268msha4c92dae11c5a4ep1ff9f5jsnd25b4424ef79",
        "x-rapidapi-host: deezerdevs-deezer.p.rapidapi.com"
    )

    @GET("search")

    fun getData(@Query("q") query:String ): Call<myData>
}