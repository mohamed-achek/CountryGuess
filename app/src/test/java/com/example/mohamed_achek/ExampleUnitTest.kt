package com.example.mohamed_achek

import org.junit.Test
import org.junit.Before
import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}

class CountryGameViewModelTest {
    private lateinit var viewModel: CountryGameViewModel

    @Before
    fun setup() {
        viewModel = CountryGameViewModel()
    }

    @Test
    fun answerCountry_correctAnswer_increasesScore() {
        val correctName = viewModel.currentCountry.name
        viewModel.answerCountry(correctName)
        assertTrue(viewModel.lastAnswerCorrect)
        assertEquals(1, viewModel.score)
    }

    @Test
    fun answerCountry_wrongAnswer_doesNotIncreaseScore() {
        val wrongName = "NotACountry"
        viewModel.answerCountry(wrongName)
        assertFalse(viewModel.lastAnswerCorrect)
        assertEquals(0, viewModel.score)
    }

    @Test
    fun nextQuestion_increasesQuestionNumber() {
        val initial = viewModel.questionNumber
        viewModel.nextQuestion()
        assertEquals(initial + 1, viewModel.questionNumber)
    }

    @Test
    fun resetGame_resetsScoreAndIndex() {
        viewModel.answerCountry(viewModel.currentCountry.name)
        viewModel.nextQuestion()
        viewModel.resetGame()
        assertEquals(0, viewModel.score)
        assertEquals(1, viewModel.questionNumber)
    }

    @Test
    fun isGameOver_returnsTrueAfter10Questions() {
        repeat(10) { viewModel.nextQuestion() }
        assertTrue(viewModel.isGameOver())
    }
}
