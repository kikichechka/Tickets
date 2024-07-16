package com.example.tickets.data.datasource

import com.example.tickets.data.MyRetrofitAllTickets
import com.example.tickets.data.dto.TicketsDTO
import retrofit2.Response
import javax.inject.Inject

class TicketsRemoteDataSource @Inject constructor(
    private val myRetrofitAllTickets: MyRetrofitAllTickets
) {

    suspend fun getData() : Response<TicketsDTO> {
        return myRetrofitAllTickets.searchData.getAllTickets()
    }
}
