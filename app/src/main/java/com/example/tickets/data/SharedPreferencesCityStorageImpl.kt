package com.example.tickets.data

import android.content.Context
import com.example.tickets.domain.repository.SharedPreferencesCityStorage
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPreferencesCityStorageImpl @Inject constructor (@ApplicationContext val context: Context) :
    SharedPreferencesCityStorage {
    private val sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
    override fun saveNameCity(city: String) {
        sharedPreferences.edit().putString(KEY_NAME, city).apply()
    }

    override fun getNameCity(): String {
        return sharedPreferences.getString(KEY_NAME, DEFAULT_NAME) ?: DEFAULT_NAME
    }

    companion object{
        private const val SHARED_PREFS_NAME = "shared prefs name"
        private const val KEY_NAME = "key name"
        private const val DEFAULT_NAME = "Москва"
    }
}
