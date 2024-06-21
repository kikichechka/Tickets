package com.example.tickets.presentation.showallticketsfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tickets.domain.GetAllTicketsUseCase
import com.example.tickets.domain.models.TicketDescription
import com.example.tickets.presentation.models.StateLoad
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ShowAllTicketsViewModel @Inject constructor(
    private val getAllTicketsUseCase: GetAllTicketsUseCase
) : ViewModel() {
    private val _allTickets = MutableStateFlow(listOf<TicketDescription>())
    val allTickets = _allTickets.asStateFlow()

    private val _state = MutableStateFlow(StateLoad.SUCCESS)
    val state = _state.asStateFlow()

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            val answer = getAllTicketsUseCase.getData()?.let {
                _allTickets.value = it
                _state.value = StateLoad.SUCCESS
            }
            if (answer == null) {
                _state.value = StateLoad.ERROR
            }
        }
    }
}
