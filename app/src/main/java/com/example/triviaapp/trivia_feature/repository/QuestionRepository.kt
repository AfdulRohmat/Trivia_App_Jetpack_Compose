package com.example.triviaapp.trivia_feature.repository

import android.util.Log
import com.example.triviaapp.data.DataOrException
import com.example.triviaapp.trivia_feature.model.QuestionModelItem
import com.example.triviaapp.trivia_feature.network.QuestionApi
import javax.inject.Inject

class QuestionRepository @Inject constructor(private val questionApi: QuestionApi) {

    private val dataOrException =
        DataOrException<ArrayList<QuestionModelItem>, Boolean, Exception>()

    suspend fun getAllQuestionData(): DataOrException<ArrayList<QuestionModelItem>, Boolean, Exception> {
        try {
            dataOrException.loading = true
            dataOrException.data = questionApi.getAllQuestion()

            if (dataOrException.data.toString().isNotEmpty()) {
                dataOrException.loading = false
            }

        } catch (e: Exception) {
            dataOrException.error = e
            Log.d(
                "Error getData",
                "getAllQuestionData: ${dataOrException.error!!.localizedMessage}"
            )

        }

        return dataOrException

    }

}