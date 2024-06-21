package com.example.tickets.domain.repository

import com.example.tickets.presentation.models.PopularDestination

interface PopularDestinationsLocalStorage {
    fun getListPopularDestination() : List<PopularDestination>
}
