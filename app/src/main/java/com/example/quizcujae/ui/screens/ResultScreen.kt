// app/src/main/java/com/example/quizcujae/ui/screens/ResultScreen.kt
package com.example.quizcujae.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ResultScreen(
    score: Int,
    correctAnswers: Int,
    totalQuestions: Int,
    onRestart: () -> Unit
) {
    val percentage = (correctAnswers.toFloat() / totalQuestions) * 100
    val emoji = when {
        percentage >= 80 -> "🏆"
        percentage >= 60 -> "🌟"
        percentage >= 40 -> "👍"
        else -> "📚"
    }

    val message = when {
        percentage >= 80 -> "¡Excelente! Eres un experto en Cuba"
        percentage >= 60 -> "¡Bien hecho! Sigue aprendiendo"
        percentage >= 40 -> "No está mal, pero puedes mejorar"
        else -> "¡Sigue practicando! Cada día aprendes más"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "$emoji ¡Quiz Completado!",
            fontSize = 32.sp,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Puntuación: $score puntos",
            fontSize = 28.sp,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Respuestas correctas: $correctAnswers/$totalQuestions",
            fontSize = 20.sp,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = message,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onRestart,
            modifier = Modifier.size(width = 200.dp, height = 56.dp)
        ) {
            Text("🔄 Intentar de nuevo", fontSize = 18.sp)
        }
    }
}