package com.example.tickets.data

import com.example.tickets.data.dto.OffersDTO
import com.example.tickets.data.dto.TicketsOffersDTO
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import javax.inject.Inject

private const val BASE_URL = "https://drive.usercontent.google.com"

class MyRetrofit @Inject constructor() {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val searchData: SearchDataApi = retrofit.create(SearchDataApi::class.java)
}

interface SearchDataApi {
    @GET("/u/0/uc?id=1o1nX3uFISrG1gR-jr_03Qlu4_KEZWhav&export=download")
    suspend fun getOffers(): Response<OffersDTO>

    @GET("/u/0/uc?id=13WhZ5ahHBwMiHRXxWPq-bYlRVRwAujta&export=download")
    suspend fun getRecommendTickets(): Response<TicketsOffersDTO>
}
