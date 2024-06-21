package com.example.tickets.presentation.searchcountryselectedfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class SearchCountrySelectedViewModelsFactory @Inject constructor(
    private val searchCountrySelectedViewModel: SearchCountrySelectedViewModel
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchCountrySelectedViewModel::class.java)) {
            return searchCountrySelectedViewModel as T
        }
        throw IllegalAccessException("Unknown class name")
    }
}
