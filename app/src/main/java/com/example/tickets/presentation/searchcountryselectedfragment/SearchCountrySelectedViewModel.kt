package com.example.tickets.presentation.searchcountryselectedfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tickets.domain.GetRecommendationsPlacesArrivalUseCase
import com.example.tickets.domain.models.TicketOfferWithPicture
import com.example.tickets.presentation.models.StateLoad
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchCountrySelectedViewModel @Inject constructor(
    private val getRecommendationsPlacesArrivalUseCase: GetRecommendationsPlacesArrivalUseCase
) : ViewModel() {
    private val _listRecommendationsPlacesArrival =
        MutableStateFlow(listOf<TicketOfferWithPicture>())
    val listRecommendationsPlacesArrival = _listRecommendationsPlacesArrival.asStateFlow()

    private val _state = MutableStateFlow(StateLoad.SUCCESS)
    val state = _state.asStateFlow()

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            val answer = getRecommendationsPlacesArrivalUseCase.getData()?.let {
                _listRecommendationsPlacesArrival.value = it
                _state.value = StateLoad.SUCCESS
            }
            if (answer == null) {
                _state.value = StateLoad.ERROR
            }
        }

    }
}
