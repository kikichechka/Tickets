package com.example.tickets.domain

import com.example.tickets.domain.repository.SharedPreferencesCityStorage
import javax.inject.Inject

class GetCityUseCase @Inject constructor(
    private val sharedPreferencesCityStorage: SharedPreferencesCityStorage
) {
    fun getCityName() : String {
        return sharedPreferencesCityStorage.getNameCity()
    }
}
