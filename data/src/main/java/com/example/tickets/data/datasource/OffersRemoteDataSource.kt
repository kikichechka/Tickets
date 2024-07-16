package com.example.tickets.data.datasource

import com.example.tickets.data.MyRetrofit
import com.example.tickets.data.dto.OffersDTO
import retrofit2.Response
import javax.inject.Inject

class OffersRemoteDataSource @Inject constructor(private val myRetrofit: MyRetrofit) {
    suspend fun getData(): Response<OffersDTO> {
        return myRetrofit.searchData.getOffers()
    }
}
