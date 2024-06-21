package com.example.tickets.presentation.airticketsfragment

import androidx.lifecycle.ViewModel
import com.example.tickets.domain.GetPopularDestinationUseCase
import com.example.tickets.domain.models.PopularDestination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class CustomSearchDialogViewModel @Inject constructor(
    getPopularDestinationUseCase: GetPopularDestinationUseCase
): ViewModel() {
    private val _listPopularDestination = MutableStateFlow(listOf<PopularDestination>())
    val listPopularDestination = _listPopularDestination.asStateFlow()

    init {
        _listPopularDestination.value = getPopularDestinationUseCase.getList()
    }
}
