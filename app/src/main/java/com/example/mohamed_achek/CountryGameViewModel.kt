package com.example.mohamed_achek

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class Country(
    val name: String,
    val flagRes: Int,
    val feature: String
)

class CountryGameViewModel : ViewModel() {
    private val countries = CountryData.countries.shuffled()

    private var currentIndex by mutableIntStateOf(0)
    var score by mutableIntStateOf(0)
        private set
    var lastAnswerCorrect by mutableStateOf(false)
        private set

    val currentCountry: Country
        get() = countries[currentIndex]

    val questionNumber: Int
        get() = currentIndex + 1

    fun answerCountry(answer: String?) {
        lastAnswerCorrect = answer?.trim()?.equals(currentCountry.name, ignoreCase = true) == true
        if (lastAnswerCorrect) score++
    }

    fun nextQuestion() {
        if (currentIndex < countries.size - 1) {
            currentIndex++
        }
    }

    fun isGameOver(): Boolean = questionNumber > 10

    fun resetGame() {
        currentIndex = 0
        score = 0
        lastAnswerCorrect = false
    }
}
