package com.example.tickets.domain.models

class OfferWithPicture(
    val picture: Int,
    override val id: Int,
    override val title: String,
    val town: String,
    override val price: Value
) : Ticket
