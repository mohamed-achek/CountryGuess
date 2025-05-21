package com.example.mohamed_achek


import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithContentDescription
import org.junit.Rule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.testing.TestNavHostController
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.platform.LocalContext

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.mohamed_achek", appContext.packageName)
    }
}

class CountryGameInstrumentedTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun questionScreen_displaysFlagAndInput() {
        val viewModel = CountryGameViewModel()
        composeTestRule.setContent {
            QuestionScreen(viewModel = viewModel, onAnswer = {})
        }
        composeTestRule.onNodeWithText("Which country is this?").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Flag of ${viewModel.currentCountry.name}").assertIsDisplayed()
        composeTestRule.onNodeWithText("Enter country name").assertIsDisplayed()
    }
}

class NavigationInstrumentedTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun navigation_fromQuestionToResultToScore() {
        // Wait for QuestionScreen to appear
        composeTestRule.onNodeWithText("Which country is this?").assertIsDisplayed()

        // Enter a wrong answer and submit
        composeTestRule.onNodeWithText("Enter country name").performTextInput("wrong")
        composeTestRule.onNodeWithText("Submit").performClick()

        // Dialog appears, press Continue
        composeTestRule.onNodeWithText("Continue").performClick()

        // Should navigate to ResultScreen
        composeTestRule.onNodeWithText("Wrong!").assertIsDisplayed()
        composeTestRule.onNodeWithText("Next").performClick()

        // Repeat until score screen
        repeat(9) {
            composeTestRule.onNodeWithText("Which country is this?").assertIsDisplayed()
            composeTestRule.onNodeWithText("Enter country name").performTextInput("wrong")
            composeTestRule.onNodeWithText("Submit").performClick()
            composeTestRule.onNodeWithText("Continue").performClick()
            composeTestRule.onNodeWithText("Wrong!").assertIsDisplayed()
            composeTestRule.onNodeWithText("Next").performClick()
        }

        // After 10 questions, should navigate to ScoreScreen
        composeTestRule.onNodeWithText("Game Over!").assertIsDisplayed()
        composeTestRule.onNodeWithText("Your score: 0/10").assertIsDisplayed()
    }
}
