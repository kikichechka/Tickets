package com.example.tickets.data.datasource

import com.example.tickets.data.MyRetrofit
import com.example.tickets.data.dto.TicketsOffersDTO
import retrofit2.Response
import javax.inject.Inject

class TicketsOffersRemoteDataSource @Inject constructor(
    private val myRetrofit: MyRetrofit
) {

    suspend fun getData(): Response<TicketsOffersDTO> {
        return myRetrofit.searchData.getRecommendTickets()
    }
}