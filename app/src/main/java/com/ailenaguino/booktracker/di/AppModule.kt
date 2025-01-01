package com.ailenaguino.booktracker.di

import com.ailenaguino.booktracker.common.Constants
import com.ailenaguino.booktracker.feature_search_book.data.SearchBookProviderImpl
import com.ailenaguino.booktracker.feature_search_book.data.remote.SearchBookApiService
import com.ailenaguino.booktracker.feature_search_book.domain.SearchBookProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //SEARCH BOOK
    @Provides
    fun provideSearchBookApi(retrofit: Retrofit): SearchBookApiService{
        return retrofit.create(SearchBookApiService::class.java)
    }

    @Provides
    fun provideSearchBookProvider(searchBookApiService: SearchBookApiService): SearchBookProvider{
        return SearchBookProviderImpl(searchBookApiService)
    }
}