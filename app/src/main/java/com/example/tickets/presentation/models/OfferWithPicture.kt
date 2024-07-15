package com.example.tickets.presentation.models

import com.example.tickets.R
import com.example.tickets.domain.models.Offers

class OfferWithPicture(
    val picture: Int,
    val id: Int,
    val title: String,
    val town: String,
    val price: Value
)

fun Offers.toOfferWithPicture(): List<OfferWithPicture> {
    val listPicture = listOf(R.drawable.bcae091d, R.drawable.socrat_i_lera, R.drawable.bcae091e)
    val list = List(offers.size) {
        OfferWithPicture(
            listPicture[it],
            offers[it].id,
            offers[it].title,
            offers[it].town,
            Value(offers[it].price.value)
        )
    }
    return list
}
