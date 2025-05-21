package com.example.mohamed_achek

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

// Data class representing a country with its name, flag, capital, and feature
data class Country(
    val name: String,
    val flagRes: Int,
    val capital: String,
    val feature: String
)

// ViewModel to manage the game state and logic
class CountryGameViewModel : ViewModel() {
    // Shuffle countries for each game session
    private val countries = CountryData.countries.shuffled()

    private var currentIndex by mutableIntStateOf(0)
    var score by mutableIntStateOf(0)
        private set
    var lastAnswerCorrect by mutableStateOf(false)
        private set

    // Get the current country for the question
    val currentCountry: Country
        get() = countries[currentIndex]

    // Get the current question number (1-based)
    val questionNumber: Int
        get() = currentIndex + 1

    // Check if the user's answer is correct and update score
    fun answerCountry(answer: String?) {
        lastAnswerCorrect = answer?.trim()?.equals(currentCountry.name, ignoreCase = true) == true
        if (lastAnswerCorrect) score++
    }

    // Move to the next question
    fun nextQuestion() {
        if (currentIndex < countries.size - 1) {
            currentIndex++
        }
    }

    // Check if the game is over (after 10 questions)
    fun isGameOver(): Boolean = questionNumber > 10

    // Reset the game state
    fun resetGame() {
        currentIndex = 0
        score = 0
        lastAnswerCorrect = false
    }

    // Set the current question number (used for lifecycle restoration)
    fun setQuestionNumber(number: Int) {
        // Clamp to valid range
        val idx = (number - 1).coerceIn(0, countries.size - 1)
        currentIndex = idx
    }
}

