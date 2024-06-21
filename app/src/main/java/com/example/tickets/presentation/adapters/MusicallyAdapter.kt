package com.example.tickets.presentation.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.tickets.domain.models.OfferWithPicture
import com.example.tickets.R
import com.example.tickets.databinding.ItemMusicallyBinding

class MusicallyAdapter(private val context: Context) :
    RecyclerView.Adapter<MusicallyAdapter.MusicallyViewHolder>() {
    private var list: List<OfferWithPicture> = listOf()
    @SuppressLint("NotifyDataSetChanged")
    fun setList(newList: List<OfferWithPicture>) {
        this.list = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicallyViewHolder {
        return MusicallyViewHolder(
            ItemMusicallyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("CheckResult", "SetTextI18n")
    override fun onBindViewHolder(holder: MusicallyViewHolder, position: Int) {
        val item = list[position]
        with(holder.binding) {
            Glide.with(image)
                .load(item.picture)
                .centerCrop()
                .into(image)
            title.text = item.title
            town.text = item.town
            priceValue.text =
                "${context.getString(R.string.from)} ${item.price.value} ${context.getString(
                    R.string.ruble_sign)}"
        }
    }

    class MusicallyViewHolder(val binding: ItemMusicallyBinding) : ViewHolder(binding.root)
}
