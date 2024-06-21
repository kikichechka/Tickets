package com.example.tickets.di

import com.example.tickets.data.TicketsRepositoryImpl
import com.example.tickets.domain.GetAllTicketsUseCase
import com.example.tickets.domain.GetCityUseCase
import com.example.tickets.domain.GetOffersToFlyUseCase
import com.example.tickets.domain.GetPopularDestinationUseCase
import com.example.tickets.domain.GetRecommendationsPlacesArrivalUseCase
import com.example.tickets.domain.SaveCityUseCase
import com.example.tickets.domain.repository.PopularDestinationsLocalStorage
import com.example.tickets.domain.repository.SharedPreferencesCityStorage
import com.example.tickets.domain.repository.TicketsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Singleton
    fun provideGetAllTicketsUseCase(ticketsRepository: TicketsRepository) : GetAllTicketsUseCase {
        return GetAllTicketsUseCase(ticketsRepository = ticketsRepository)
    }

    @Provides
    @Singleton
    fun provideGetCityUseCase(sharedPreferencesCityStorage: SharedPreferencesCityStorage) : GetCityUseCase {
        return GetCityUseCase(sharedPreferencesCityStorage = sharedPreferencesCityStorage)
    }

    @Provides
    @Singleton
    fun provideGetOffersToFlyUseCase(ticketsRepository: TicketsRepository) : GetOffersToFlyUseCase {
        return GetOffersToFlyUseCase(ticketsRepository = ticketsRepository)
    }

    @Provides
    @Singleton
    fun provideGetPopularDestinationUseCase(popularDestinationsLocalStorage: PopularDestinationsLocalStorage) : GetPopularDestinationUseCase {
        return GetPopularDestinationUseCase(popularDestinationsLocalStorage = popularDestinationsLocalStorage)
    }

    @Provides
    @Singleton
    fun provideGetRecommendationsPlacesArrivalUseCase(ticketsRepository: TicketsRepository) : GetRecommendationsPlacesArrivalUseCase {
        return GetRecommendationsPlacesArrivalUseCase(ticketsRepository = ticketsRepository)
    }

    @Provides
    @Singleton
    fun provideSaveCityUseCase(sharedPreferencesCityStorage: SharedPreferencesCityStorage) : SaveCityUseCase {
        return SaveCityUseCase(sharedPreferencesCityStorage = sharedPreferencesCityStorage)
    }
}
