package com.example.tickets.domain

import com.example.tickets.domain.repository.PopularDestinationsLocalStorage
import javax.inject.Inject

class GetPopularDestinationUseCase @Inject constructor(
    private val popularDestinationsLocalStorage: PopularDestinationsLocalStorage
) {
    fun getList() = popularDestinationsLocalStorage.getListPopularDestination()
}
