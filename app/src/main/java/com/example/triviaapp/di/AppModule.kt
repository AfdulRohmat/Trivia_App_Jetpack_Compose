package com.example.triviaapp.di

import com.example.triviaapp.trivia_feature.network.QuestionApi
import com.example.triviaapp.trivia_feature.repository.QuestionRepository
import com.example.triviaapp.utils.Constants
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

    // PROVIDE NETWORK --RETROFIT
    @Singleton
    @Provides
    fun provideTriviaApi(): QuestionApi {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(QuestionApi::class.java)
    }

    // PROVIDE ALL REPOSITORY
    @Singleton
    @Provides
    fun provideQuestionRepository(questionApi: QuestionApi) = QuestionRepository(questionApi)


}