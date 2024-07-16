package com.example.tickets.data.dto

import com.example.tickets.domain.models.Offer

class OfferDTO(
    val id: Int,
    val title: String,
    val town: String,
    val price: ValueDTO
)

fun OfferDTO.toModel(): Offer {
    return Offer(
        id = id,
        title = title,
        town = town,
        price = price.toModel()
    )
}
