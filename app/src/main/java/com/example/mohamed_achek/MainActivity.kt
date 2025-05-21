package com.example.mohamed_achek

import QuestionScreen
import ResultScreen
import ScoreScreen
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mohamed_achek.ui.theme.Mohamed_AchekTheme
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mohamed_AchekTheme {
                val navController = rememberNavController()
                val viewModel: CountryGameViewModel = viewModel()
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    CountryGameNavHost(navController, viewModel)
                }
            }
        }
    }
}

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
            QuestionScreen(
                viewModel = viewModel,
                onAnswer = {
                    navController.navigate("result")
                }
            )
        }
        composable("result") {
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
