package com.example.tickets.domain.repository

import com.example.tickets.domain.models.PopularDestination


interface PopularDestinationsLocalStorage {
    fun getListPopularDestination(): List<PopularDestination>
}
