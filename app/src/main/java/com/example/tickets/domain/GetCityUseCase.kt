package com.example.tickets.domain

import com.example.tickets.data.SharedPreferencesCityStorageImpl
import javax.inject.Inject

class GetCityUseCase @Inject constructor(
    private val sharedPreferencesCityStorageImpl: SharedPreferencesCityStorageImpl
) {
    fun getCityName() : String {
        return sharedPreferencesCityStorageImpl.getNameCity()
    }
}
