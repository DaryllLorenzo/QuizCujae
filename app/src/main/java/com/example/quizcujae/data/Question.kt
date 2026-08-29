package com.example.quizcujae.data

data class Question(
    val id: Int,
    val text: String,
    val options: List<String>,
    val correctAnswerIndex: Int,
    val questionCategory: QuestionCategory
)
