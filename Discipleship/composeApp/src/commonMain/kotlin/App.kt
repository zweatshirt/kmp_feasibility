import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview
import discipleship.WelcomeScreen // Keep to use for testing welcome Screen
import disciple.introQuestions.DiscipleQuestions // keep to use for testing disciple Questions
import discipler.introQuestions.DisciplerQuestions // keep to use for testing discipler Questions

@Composable
@Preview


// At some point soon we need to define navigation
fun App() {
    MaterialTheme {
        Scaffold {
            // All of our main screen composables can go here
            // WelcomeScreen()
            // LoginScreen()
            // SignupScreen()
            // DorD()
            // DiscipleQuestions()
            // DisciplerQuestions()
            HomeScreen()
        }
    }
}