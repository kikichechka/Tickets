package com.example.tickets.presentation.showallticketsfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class ShowAllTicketsViewModelFactory @Inject constructor(
    private val showAllTicketsViewModel: ShowAllTicketsViewModel
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShowAllTicketsViewModel::class.java)) {
            return showAllTicketsViewModel as T
        }
        throw IllegalAccessException("Unknown class name")
    }
}
