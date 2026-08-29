// app/src/main/java/com/example/quizcujae/ui/screens/QuizScreen.kt
package com.example.quizcujae.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizcujae.data.Question

@Composable
fun QuizScreen(
    questions: List<Question>,
    onQuizComplete: (Int, Int) -> Unit
) {
    var currentQuestionIndex by remember { mutableIntStateOf(0) }
    var selectedOptionIndex by remember { mutableStateOf<Int?>(null) }
    var score by remember { mutableIntStateOf(0) }
    var answered by remember { mutableStateOf(false) }
    var correctAnswers by remember { mutableIntStateOf(0) }

    val currentQuestion = questions[currentQuestionIndex]
    val totalQuestions = questions.size
    val progress = (currentQuestionIndex + 1).toFloat() / totalQuestions

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top
    ) {
        // Barra de progreso
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Pregunta ${currentQuestionIndex + 1}/$totalQuestions",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Puntuación: $score",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Categoría
        Text(
            text = "📌 ${currentQuestion.questionCategory.displayName}",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Pregunta
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Text(
                text = currentQuestion.text,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Opciones
        currentQuestion.options.forEachIndexed { index, option ->
            val isSelected = selectedOptionIndex == index
            val isCorrect = answered && index == currentQuestion.correctAnswerIndex
            val isWrong = answered && isSelected && !isCorrect

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                onClick = {
                    if (!answered) {
                        selectedOptionIndex = index
                    }
                },
                colors = CardDefaults.cardColors(
                    containerColor = when {
                        isCorrect -> Color.Green.copy(alpha = 0.2f)
                        isWrong -> Color.Red.copy(alpha = 0.2f)
                        else -> MaterialTheme.colorScheme.surfaceVariant
                    }
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = if (isSelected) 8.dp else 0.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = isSelected,
                        onClick = if (!answered) { { selectedOptionIndex = index } } else null,
                        enabled = !answered
                    )
                    Text(
                        text = option,
                        modifier = Modifier.padding(start = 8.dp),
                        fontSize = 16.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botones
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (!answered && selectedOptionIndex != null) {
                Button(
                    onClick = {
                        answered = true
                        val isCorrect = selectedOptionIndex == currentQuestion.correctAnswerIndex
                        if (isCorrect) {
                            score += 10
                            correctAnswers++
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp)
                ) {
                    Text("Responder")
                }
            }

            if (answered) {
                Button(
                    onClick = {
                        if (currentQuestionIndex < totalQuestions - 1) {
                            currentQuestionIndex++
                            selectedOptionIndex = null
                            answered = false
                        } else {
                            onQuizComplete(score, correctAnswers)
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp)
                ) {
                    Text(
                        if (currentQuestionIndex < totalQuestions - 1)
                            "Siguiente →"
                        else
                            "Ver resultados 🏆"
                    )
                }
            }
        }
    }
}