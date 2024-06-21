package com.example.tickets.data

import com.example.tickets.domain.models.Offers
import com.example.tickets.domain.models.Tickets
import com.example.tickets.domain.models.TicketsOffers
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url


private const val BASE_URL = "https://run.mocky.io/"

object MyRetrofit {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val searchData: SearchDataApi = retrofit.create(SearchDataApi::class.java)
}

interface SearchDataApi {
    @GET
    suspend fun getOffers(@Url url: String = "v3/ad9a46ba-276c-4a81-88a6-c068e51cce3a"): Response<Offers>

    @GET
    suspend fun getRecommendTickets(@Url url: String = "v3/38b5205d-1a3d-4c2f-9d77-2f9d1ef01a4a"): Response<TicketsOffers>

    @GET
    suspend fun getAllTickets(@Url url: String = "v3/c0464573-5a13-45c9-89f8-717436748b69"): Response<Tickets>
}
