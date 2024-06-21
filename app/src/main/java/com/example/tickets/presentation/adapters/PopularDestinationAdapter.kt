package com.example.tickets.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.tickets.databinding.ItemForPopularDestinationBinding
import com.example.tickets.domain.models.PopularDestination

class PopularDestinationAdapter(
    private val list: List<PopularDestination>,
    private val click: (name: String) -> Unit
) :
    RecyclerView.Adapter<PopularDestinationAdapter.CustomSearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomSearchViewHolder {
        return CustomSearchViewHolder(
            ItemForPopularDestinationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CustomSearchViewHolder, position: Int) {
        val popularDestination = list[position]
        with(holder.binding) {
            Glide.with(image)
                .load(popularDestination.image)
                .centerCrop()
                .into(image)
            cityName.text = popularDestination.cityName
            characteristic.text = popularDestination.characteristic
            root.setOnClickListener { click(popularDestination.cityName) }
        }
    }

    class CustomSearchViewHolder(val binding: ItemForPopularDestinationBinding) :
        ViewHolder(binding.root)
}
