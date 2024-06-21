package com.example.tickets.data

import android.content.Context
import com.example.tickets.domain.models.PopularDestination
import com.example.tickets.domain.repository.PopularDestinationsLocalStorage
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PopularDestinationsLocalStorageImpl @Inject constructor(@ApplicationContext val context: Context) :
    PopularDestinationsLocalStorage {
    override fun getListPopularDestination(): List<PopularDestination> {
        return listOf(
            PopularDestination(
                R.drawable.istanbul,
                context.resources.getString(R.string.istanbul),
                context.resources.getString(R.string.popular_destination)
            ),
            PopularDestination(
                R.drawable.sochi, context.resources.getString(R.string.sochi),
                context.resources.getString(R.string.popular_destination)
            ),
            PopularDestination(
                R.drawable.phuket, context.resources.getString(R.string.phuket),
                context.resources.getString(R.string.popular_destination)
            )
        )
    }
}
