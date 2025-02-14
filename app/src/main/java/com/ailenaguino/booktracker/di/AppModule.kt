package com.ailenaguino.booktracker.di

import android.content.Context
import androidx.room.Room
import com.ailenaguino.booktracker.common.Constants
import com.ailenaguino.booktracker.feature_add_book.data.AddBookRepositoryImpl
import com.ailenaguino.booktracker.feature_add_book.data.database.BookDao
import com.ailenaguino.booktracker.feature_add_book.data.database.BookDatabase
import com.ailenaguino.booktracker.feature_add_book.domain.AddBookRepository
import com.ailenaguino.booktracker.feature_home.data.GetBooksRepositoryImpl
import com.ailenaguino.booktracker.feature_home.domain.GetBooksRepository
import com.ailenaguino.booktracker.feature_search_book.data.SearchBookProviderImpl
import com.ailenaguino.booktracker.feature_search_book.data.remote.SearchBookApiService
import com.ailenaguino.booktracker.feature_search_book.domain.SearchBookProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val DATABASE_NAME = "book_tracker_database"

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

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, BookDatabase::class.java, DATABASE_NAME).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideBookDao(bookDatabase: BookDatabase) = bookDatabase.bookDao()

    @Provides
    fun provideAddBookRepository(bookDao: BookDao): AddBookRepository {
        return AddBookRepositoryImpl(bookDao)
    }

    @Provides
    fun provideGetBooksRepository(bookDao: BookDao): GetBooksRepository {
        return GetBooksRepositoryImpl(bookDao)
    }
}