package com.example.tickets.data

import com.example.tickets.domain.models.Offers
import com.example.tickets.domain.models.Tickets
import com.example.tickets.domain.models.TicketsOffers
import com.example.tickets.domain.repository.TicketsRepository
import retrofit2.Response
import javax.inject.Inject

class TicketsRepositoryImpl @Inject constructor() : TicketsRepository {
    override suspend fun getOffers(): Response<Offers> {
        return MyRetrofit.searchData.getOffers()
    }

    override suspend fun getOffers1(): Response<Offers> {
        return MyRetrofit.searchData.getOffers()
    }

    override suspend fun getRecommendTickets(): Response<TicketsOffers> {
        return MyRetrofit.searchData.getRecommendTickets()
    }

    override suspend fun getAllTickets(): Response<Tickets> {
        return MyRetrofitAllTickets.searchData.getAllTickets()
    }
}
