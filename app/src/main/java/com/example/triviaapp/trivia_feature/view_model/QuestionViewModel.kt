package com.example.triviaapp.trivia_feature.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.triviaapp.data.DataOrException
import com.example.triviaapp.trivia_feature.model.QuestionModelItem
import com.example.triviaapp.trivia_feature.network.QuestionApi
import com.example.triviaapp.trivia_feature.repository.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// INJECT CONSTRUCTOR / GET DATA FROM REPOSITORY
@HiltViewModel
class QuestionViewModel @Inject constructor(private val questionRepository: QuestionRepository) :
    ViewModel() {

    var data: MutableState<DataOrException<ArrayList<QuestionModelItem>, Boolean, Exception>> =
        mutableStateOf(
            DataOrException(null, true, Exception(""))
        )

    init {
        getAllQuestionData()
    }

    private fun getAllQuestionData() {
        viewModelScope.launch {
            data.value.loading = true
            data.value = questionRepository.getAllQuestionData()

            if (data.value.data.toString().isNotEmpty()) {
                data.value.loading = false
            }
        }


    }


}