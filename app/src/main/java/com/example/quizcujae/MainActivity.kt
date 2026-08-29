// app/src/main/java/com/example/quizcujae/MainActivity.kt
package com.example.quizcujae

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.quizcujae.data.QuestionRepository
import com.example.quizcujae.ui.screens.QuizScreen
import com.example.quizcujae.ui.screens.ResultScreen
import com.example.quizcujae.ui.screens.WelcomeScreen
import com.example.quizcujae.ui.theme.QuizCujaeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuizCujaeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    QuizApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun QuizApp(modifier: Modifier = Modifier) {
    // Estado de navegación
    var currentScreen by remember { mutableStateOf("welcome") }
    var finalScore by remember { mutableStateOf(0) }
    var finalCorrectAnswers by remember { mutableStateOf(0) }
    val questions = QuestionRepository.getQuestions()

    when (currentScreen) {
        "welcome" -> {
            WelcomeScreen(
                onStartQuiz = {
                    currentScreen = "quiz"
                }
            )
        }
        "quiz" -> {
            QuizScreen(
                questions = questions,
                onQuizComplete = { score, correctAnswers ->
                    finalScore = score
                    finalCorrectAnswers = correctAnswers
                    currentScreen = "result"
                }
            )
        }
        "result" -> {
            ResultScreen(
                score = finalScore,
                correctAnswers = finalCorrectAnswers,
                totalQuestions = questions.size,
                onRestart = {
                    finalScore = 0
                    finalCorrectAnswers = 0
                    currentScreen = "welcome"
                }
            )
        }
    }
}

// Preview para pruebas rápidas
@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    QuizCujaeTheme {
        WelcomeScreen(onStartQuiz = {})
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    QuizCujaeTheme {
        ResultScreen(
            score = 30,
            correctAnswers = 3,
            totalQuestions = 6,
            onRestart = {}
        )
    }
}