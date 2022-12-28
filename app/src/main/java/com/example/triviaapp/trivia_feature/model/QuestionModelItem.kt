package com.example.triviaapp.trivia_feature.model

data class QuestionModelItem(
    val answer: String,
    val category: String,
    val choices: List<String>,
    val question: String
)