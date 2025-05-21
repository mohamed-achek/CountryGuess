package com.example.mohamed_achek

import QuestionScreen
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
