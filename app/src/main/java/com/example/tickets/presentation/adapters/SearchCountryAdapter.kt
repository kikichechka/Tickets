package com.example.tickets.presentation.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.tickets.R
import com.example.tickets.databinding.ItemStraightRailBinding
import com.example.tickets.domain.models.TicketOfferWithPicture

class SearchCountryAdapter(private val context: Context) :
    RecyclerView.Adapter<SearchCountryAdapter.SearchCountryHolder>() {
    private var list: List<TicketOfferWithPicture> = listOf()
    @SuppressLint("NotifyDataSetChanged")
    fun setList(newList: List<TicketOfferWithPicture>) {
        this.list = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchCountryHolder {
        return SearchCountryHolder(
            ItemStraightRailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: SearchCountryHolder, position: Int) {
        val ticketOffer = list[position]

        with(holder.binding) {
            Glide.with(icon)
                .load(ticketOffer.picture)
                .centerCrop()
                .into(icon)
            title.text = ticketOffer.title
            priceValue.text = "${ticketOffer.price.value} ${context.getString(R.string.ruble_sign)} "
            timeRange.text = ticketOffer.timeRange.joinToString(separator = "  ")
        }
    }

    class SearchCountryHolder(val binding: ItemStraightRailBinding) : ViewHolder(binding.root)
}
