package com.example.tickets.domain.repository

import com.example.tickets.domain.models.Offers
import com.example.tickets.domain.models.Tickets
import com.example.tickets.domain.models.TicketsOffers
import retrofit2.Response

interface TicketsRepository {
    suspend fun getOffers(): Response<Offers>

    suspend fun getOffers1(): Response<Offers>

    suspend fun getRecommendTickets(): Response<TicketsOffers>

    suspend fun getAllTickets(): Response<Tickets>
}
