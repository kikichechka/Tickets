package com.example.tickets.domain

import com.example.tickets.data.PopularDestinationsLocalStorageImpl
import javax.inject.Inject

class GetPopularDestinationUseCase @Inject constructor(
    private val popularDestinationsLocalStorageImpl: PopularDestinationsLocalStorageImpl
) {
    fun getList() = popularDestinationsLocalStorageImpl.getListPopularDestination()
}
