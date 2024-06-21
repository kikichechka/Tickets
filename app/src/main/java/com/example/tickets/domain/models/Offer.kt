package com.example.tickets.domain.models

open class Offer(
    override val id: Int,
    override val title: String,
    open val town: String,
    override val price: Value
) : Ticket
