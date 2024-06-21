package com.example.tickets.presentation.airticketsfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class AirTicketsViewModelsFactory @Inject constructor(
    private val airTicketsViewModel: AirTicketsViewModel,
    private val customSearchDialogViewModel: CustomSearchDialogViewModel
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AirTicketsViewModel::class.java)) {
            return airTicketsViewModel as T
        }
        if (modelClass.isAssignableFrom(CustomSearchDialogViewModel::class.java)) {
            return customSearchDialogViewModel as T
        }

        throw IllegalAccessException("Unknown class name")
    }
}
