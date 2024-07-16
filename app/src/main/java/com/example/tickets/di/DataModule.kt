package com.example.tickets.di

import android.content.Context
import com.example.tickets.data.PopularDestinationsLocalStorageImpl
import com.example.tickets.data.SharedPreferencesCityStorageImpl
import com.example.tickets.data.TicketsRepositoryImpl
import com.example.tickets.data.datasource.OffersRemoteDataSource
import com.example.tickets.data.datasource.TicketsOffersRemoteDataSource
import com.example.tickets.data.datasource.TicketsRemoteDataSource
import com.example.tickets.domain.repository.PopularDestinationsLocalStorage
import com.example.tickets.domain.repository.SharedPreferencesCityStorage
import com.example.tickets.domain.repository.TicketsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideTicketsRepository(
        offersRemoteDataSource: OffersRemoteDataSource,
        ticketsOffersRemoteDataSource: TicketsOffersRemoteDataSource,
        ticketsRemoteDataSource: TicketsRemoteDataSource
    ): TicketsRepository {
        return TicketsRepositoryImpl(
            offersRemoteDataSource = offersRemoteDataSource,
            ticketsOffersRemoteDataSource = ticketsOffersRemoteDataSource,
            ticketsRemoteDataSource = ticketsRemoteDataSource
        )
    }

    @Provides
    @Singleton
    fun provideSharedPreferencesCityStorage(@ApplicationContext context: Context): SharedPreferencesCityStorage {
        return SharedPreferencesCityStorageImpl(context = context)
    }

    @Provides
    @Singleton
    fun providePopularDestinationsLocalStorage(@ApplicationContext context: Context): PopularDestinationsLocalStorage {
        return PopularDestinationsLocalStorageImpl(context = context)
    }
}
