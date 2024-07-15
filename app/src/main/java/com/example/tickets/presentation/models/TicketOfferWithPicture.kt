package com.example.tickets.presentation.models

import com.example.tickets.R
import com.example.tickets.domain.models.TicketsOffers

class TicketOfferWithPicture(
    val picture: Int,
    val id: Int,
    val title: String,
    val timeRange: List<String>,
    val price: Value
)

fun TicketsOffers.toTicketOfferWithPicture(): List<TicketOfferWithPicture> {
    val listPicture =
        listOf(R.drawable.red_circle, R.drawable.blue_circle, R.drawable.white_circle)
    val list = List(3) {
        TicketOfferWithPicture(
            listPicture[it],
            ticketsOffers[it].id,
            ticketsOffers[it].title,
            ticketsOffers[it].timeRange,
            Value(ticketsOffers[it].price.value)
        )
    }
    return list
}
