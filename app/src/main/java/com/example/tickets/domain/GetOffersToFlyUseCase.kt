package com.example.tickets.domain

import com.example.tickets.data.TicketsRepositoryImpl
import com.example.tickets.domain.models.Converter
import com.example.tickets.domain.models.OfferWithPicture
import javax.inject.Inject

class GetOffersToFlyUseCase @Inject constructor(
    private val ticketsRepositoryImpl: TicketsRepositoryImpl
) {

    suspend fun getData(): List<OfferWithPicture>? {
        val response = ticketsRepositoryImpl.getOffers()
        if (response.code() in 200..299) {
            return response.body()
                ?.let { Converter.converterOfferToOfferWithPicture(it) }
        }
        return null
    }
}
