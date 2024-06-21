package com.example.tickets.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tickets.domain.models.TicketDescription
import com.example.tickets.databinding.ItemTicketBinding

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class AllTicketsAdapter :
    RecyclerView.Adapter<AllTicketsAdapter.AllTicketsViewHolder>() {
    private var list: List<TicketDescription> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun changeData(newList: List<TicketDescription>) {
        list = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllTicketsViewHolder {
        return AllTicketsViewHolder(
            ItemTicketBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: AllTicketsViewHolder, position: Int) {
        val ticketDescription = list[position]

        with(holder.binding) {
            ticketDescription.badge?.let {
                badge.visibility = View.VISIBLE
                badge.text = it
            }
            priceValue.text = ticketDescription.price.value.toString()
            departureDate.text = getDate(ticketDescription.departure.date)
            departureAirport.text = ticketDescription.departure.airport
            arrivalDate.text = getDate(ticketDescription.arrival.date)
            arrivalAirport.text = ticketDescription.arrival.airport
            timeDifference.text =
                calculateTime(ticketDescription.departure.date, ticketDescription.arrival.date)
            showTransfers(ticketDescription.hasTransfer, withoutTransfers)
        }
    }

    private fun getDate(dateTime: String): String {
        val localDate = LocalDateTime.parse(dateTime)
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        return localDate.format(formatter)
    }

    private fun calculateTime(dateTime1: String, dateTime2: String): String {
        val parsedDate1 = LocalDateTime.parse(dateTime1)
        val parsedDate2 = LocalDateTime.parse(dateTime2)
        val d5 = ChronoUnit.MINUTES.between(parsedDate1, parsedDate2)
        return "${d5 / MINUTES}.${d5 % MINUTES}ч"
    }

    private fun showTransfers(transfer: Boolean, view: View) {
        when (transfer) {
            true -> view.visibility = View.GONE
            false -> view.visibility = View.VISIBLE
        }
    }

    companion object {
        private const val MINUTES = 60
    }

    class AllTicketsViewHolder(val binding: ItemTicketBinding) : ViewHolder(binding.root)
}
