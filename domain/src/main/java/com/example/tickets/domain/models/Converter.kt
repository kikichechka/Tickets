package com.example.tickets.domain.models

import com.example.tickets.domain.R

object Converter {

    fun converterOfferToOfferWithPicture(offers: Offers): List<OfferWithPicture> {
        val listPicture = listOf(R.drawable.bcae091d, R.drawable.socrat_i_lera, R.drawable.bcae091e)
        val list = List(offers.offers.size) {
            OfferWithPicture(
                listPicture[it],
                offers.offers[it].id,
                offers.offers[it].title,
                offers.offers[it].town,
                offers.offers[it].price
            )
        }
        return list
    }

    fun converterTicketOfferToTicketOfferWithPicture(ticketsOffers: TicketsOffers): List<TicketOfferWithPicture> {
        val listPicture =
            listOf(R.drawable.red_circle, R.drawable.blue_circle, R.drawable.white_circle)
        val list = List(3) {
            TicketOfferWithPicture(
                listPicture[it],
                ticketsOffers.ticketsOffers[it].id,
                ticketsOffers.ticketsOffers[it].title,
                ticketsOffers.ticketsOffers[it].timeRange,
                ticketsOffers.ticketsOffers[it].price
            )
        }
        return list
    }
}

