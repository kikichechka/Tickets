package com.example.tickets.data

import com.example.tickets.data.dto.TicketsDTO
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import javax.inject.Inject

private const val BASE_URL = "https://drive.google.com"

class MyRetrofitAllTickets @Inject constructor() {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val searchData: SearchDataApiAllTickets = retrofit.create(SearchDataApiAllTickets::class.java)
}

interface SearchDataApiAllTickets {
    @GET("/uc?export=download&id=1HogOsz4hWkRwco4kud3isZHFQLUAwNBA")
    suspend fun getAllTickets(): Response<TicketsDTO>
}
