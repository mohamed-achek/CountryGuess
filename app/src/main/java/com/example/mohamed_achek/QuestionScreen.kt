import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mohamed_achek.CountryGameViewModel
import com.example.mohamed_achek.ui.theme.Mohamed_AchekTheme

@Composable
fun QuestionScreen(
    viewModel: CountryGameViewModel,
    onAnswer: (Boolean) -> Unit
) {
    val country = viewModel.currentCountry

    var answer by remember { mutableStateOf("") }
    var showResultDialog by remember { mutableStateOf(false) }
    var wasCorrect by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        Card(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(0.95f)
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.large,
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = Modifier
                    .padding(28.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Question ${viewModel.questionNumber}/10",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(20.dp))
                Card(
                    shape = MaterialTheme.shapes.medium,
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Image(
                        painter = painterResource(id = country.flagRes),
                        contentDescription = "Flag of ${country.name}",
                        modifier = Modifier
                            .size(140.dp)
                            .padding(16.dp)
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    "Which country is this?",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(18.dp))
                OutlinedTextField(
                    value = answer,
                    onValueChange = { answer = it },
                    label = { Text("Enter country name") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(28.dp))
                Button(
                    onClick = {
                        viewModel.answerCountry(answer)
                        wasCorrect = viewModel.lastAnswerCorrect
                        showResultDialog = true
                    },
                    enabled = answer.isNotBlank(),
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Submit", style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }

    if (showResultDialog) {
        AlertDialog(
            onDismissRequest = {
                showResultDialog = false
                onAnswer(wasCorrect)
            },
            title = {
                Text(
                    if (wasCorrect) "Correct!" else "Wrong!",
                    color = if (wasCorrect) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                )
            },
            text = null,
            confirmButton = {
                Button(
                    onClick = {
                        showResultDialog = false
                        onAnswer(wasCorrect)
                    },
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text("Continue")
                }
            },
            containerColor = MaterialTheme.colorScheme.surface,
            shape = MaterialTheme.shapes.large
        )
    }
}

@Preview(showBackground = true)
@Composable
fun QuestionScreenPreview() {
    val viewModel = CountryGameViewModel()
    Mohamed_AchekTheme {
        QuestionScreen(viewModel = viewModel, onAnswer = {})
    }
}
