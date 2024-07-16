package com.example.tickets.data

import com.example.tickets.data.datasource.OffersRemoteDataSource
import com.example.tickets.data.datasource.TicketsOffersRemoteDataSource
import com.example.tickets.data.datasource.TicketsRemoteDataSource
import com.example.tickets.data.dto.toModel
import com.example.tickets.domain.models.Offers
import com.example.tickets.domain.models.Tickets
import com.example.tickets.domain.models.TicketsOffers
import com.example.tickets.domain.repository.TicketsRepository
import javax.inject.Inject

class TicketsRepositoryImpl @Inject constructor(
    private val offersRemoteDataSource: OffersRemoteDataSource,
    private val ticketsOffersRemoteDataSource: TicketsOffersRemoteDataSource,
    private val ticketsRemoteDataSource: TicketsRemoteDataSource
) : TicketsRepository {
    override suspend fun getOffers(): Offers? {
        return offersRemoteDataSource.getData().body()?.toModel()
    }

    override suspend fun getRecommendTickets(): TicketsOffers? {
        return ticketsOffersRemoteDataSource.getData().body()?.toModel()
    }

    override suspend fun getAllTickets(): Tickets? {
        return ticketsRemoteDataSource.getData().body()?.toModel()
    }
}
