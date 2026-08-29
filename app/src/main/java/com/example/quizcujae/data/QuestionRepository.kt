package com.example.quizcujae.data

object QuestionRepository {
    fun getQuestions(): List<Question> {
        return listOf(
            Question(
                id = 1,
                text = "¿Cuál es la capital de Cuba?",
                options = listOf("La Habana", "Santiago", "Camagüey", "Holguín"),
                correctAnswerIndex = 0,
                questionCategory = QuestionCategory.GENERAL
            ),
            Question(
                id = 2,
                text = "¿En qué año llegó Cristóbal Colón a Cuba?",
                options = listOf("1492", "1493", "1494", "1495"),
                correctAnswerIndex = 0,
                questionCategory = QuestionCategory.GENERAL
            ),
            Question(
                id = 3,
                text = "¿Quién es el autor de 'La Edad de Oro'?",
                options = listOf(
                    "José Martí",
                    "Nicolás Guillén",
                    "Alejo Carpentier",
                    "José Lezama Lima"
                ),
                correctAnswerIndex = 0,
                questionCategory = QuestionCategory.GENERAL
            ),
            Question(
                id = 4,
                text = "¿Qué deporte es el más popular en Cuba?",
                options = listOf("Béisbol", "Fútbol", "Baloncesto", "Voleibol"),
                correctAnswerIndex = 0,
                questionCategory = QuestionCategory.GENERAL
            ),
            Question(
                id = 5,
                text = "¿Cuál es la moneda oficial de Cuba?",
                options = listOf("Peso cubano", "Dólar", "Euro", "Peso mexicano"),
                correctAnswerIndex = 0,
                questionCategory = QuestionCategory.GENERAL
            ),
        )
    }
}