package com.example.mohamed_achek

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mohamed_achek.ui.theme.Mohamed_AchekTheme
import androidx.compose.ui.tooling.preview.Preview

// MainActivity manages the lifecycle and navigation of the Country Game app.
class MainActivity : ComponentActivity() {
    // Store the last question number for lifecycle restoration
    private var lastQuestionNumber: Int = 1
    private lateinit var viewModel: CountryGameViewModel

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mohamed_AchekTheme {
                val navController = rememberNavController()
                // Use the same viewModel instance for lifecycle handling
                viewModel = androidx.lifecycle.viewmodel.compose.viewModel()
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    CountryGameNavHost(navController, viewModel)
                }
            }
        }
    }

    // Save the current question number when the app is paused
    override fun onPause() {
        super.onPause()
        if (::viewModel.isInitialized) {
            lastQuestionNumber = viewModel.questionNumber
        }
    }

    // Restore the question number when the app is resumed
    override fun onResume() {
        super.onResume()
        if (::viewModel.isInitialized) {
            viewModel.setQuestionNumber(lastQuestionNumber)
        }
    }
}

// Navigation host for the three main screens of the game
@Composable
fun CountryGameNavHost(
    navController: NavHostController,
    viewModel: CountryGameViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "question"
    ) {
        composable("question") {
            // Question screen: user guesses the country
            QuestionScreen(
                viewModel = viewModel,
                onAnswer = {
                    navController.navigate("result")
                }
            )
        }
        composable("result") {
            // Result screen: shows if answer is correct or wrong
            ResultScreen(
                viewModel = viewModel,
                onNext = {
                    if (viewModel.isGameOver()) {
                        navController.navigate("score") {
                            popUpTo("question") { inclusive = true }
                        }
                    } else {
                        navController.navigate("question") {
                            popUpTo("result") { inclusive = true }
                        }
                    }
                }
            )
        }
        composable("score") {
            // Score screen: shows final score and allows restart
            ScoreScreen(
                viewModel = viewModel,
                onRestart = {
                    viewModel.resetGame()
                    navController.navigate("question") {
                        popUpTo("score") { inclusive = true }
                    }
                }
            )
        }
    }
}

// Preview for navigation host
@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun CountryGameNavHostPreview() {
    Mohamed_AchekTheme {
        val navController = rememberNavController()
        val viewModel = CountryGameViewModel()
        CountryGameNavHost(navController, viewModel)
    }
}

