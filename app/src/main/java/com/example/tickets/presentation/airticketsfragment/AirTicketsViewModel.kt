package com.example.tickets.presentation.airticketsfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tickets.domain.GetCityUseCase
import com.example.tickets.domain.GetOffersToFlyUseCase
import com.example.tickets.domain.SaveCityUseCase
import com.example.tickets.domain.models.OfferWithPicture
import com.example.tickets.presentation.models.StateLoad
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AirTicketsViewModel @Inject constructor(
    private val getOffersToFlyUseCase: GetOffersToFlyUseCase,
    private val saveCityUseCase: SaveCityUseCase,
    getCityUseCase: GetCityUseCase
) : ViewModel() {
    private val _offersToFly = MutableStateFlow(listOf<OfferWithPicture>())
    val offerToFly = _offersToFly.asStateFlow()

    private val _city = MutableStateFlow("")
    val city = _city.asStateFlow()

    private val _state = MutableStateFlow(StateLoad.SUCCESS)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getData()
        }
        _city.value = getCityUseCase.getCityName()
    }

    private suspend fun getData() {
        val answer = getOffersToFlyUseCase.getData()?.let {
            _offersToFly.value = it
            _state.value = StateLoad.SUCCESS
        }
        if (answer == null) {
            _state.value = StateLoad.ERROR
        }
    }

    fun saveCityName(name: String) {
        saveCityUseCase.saveCityName(name)
    }
}
