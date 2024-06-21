package com.example.tickets.domain

import com.example.tickets.domain.repository.SharedPreferencesCityStorage
import javax.inject.Inject

class SaveCityUseCase @Inject constructor(
    private val sharedPreferencesCityStorage: SharedPreferencesCityStorage
) {
    fun saveCityName(name: String) {
        sharedPreferencesCityStorage.saveNameCity(name)
    }
}
