package com.example.tickets.domain

import com.example.tickets.data.SharedPreferencesCityStorageImpl
import javax.inject.Inject

class SaveCityUseCase @Inject constructor(
    private val sharedPreferencesCityStorageImpl: SharedPreferencesCityStorageImpl
) {
    fun saveCityName(name: String) {
        sharedPreferencesCityStorageImpl.saveNameCity(name)
    }
}
