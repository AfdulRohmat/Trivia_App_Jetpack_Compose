package com.example.triviaapp.trivia_feature.network

import com.example.triviaapp.trivia_feature.model.QuestionModel
import retrofit2.http.GET

interface QuestionApi {

    // DOING CRUD API OPERATION

    @GET("world.json")
    suspend fun getAllQuestion(): QuestionModel
}