package com.example.tickets.data.dto

import com.example.tickets.domain.models.Offers

data class OffersDTO(
    val offers: List<OfferDTO>
)

fun OffersDTO.toModel(): Offers {
    return Offers(offers = offers.map { it.toModel() })
}
