package com.example.tickets.domain.repository

interface SharedPreferencesCityStorage {

    fun saveNameCity(city: String)
    fun getNameCity() : String
}
