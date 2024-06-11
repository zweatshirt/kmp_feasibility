import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview
import discipleship.WelcomeScreen

@Composable
@Preview


// At some point soon we need to define navigation
fun App() {
    MaterialTheme {
        Scaffold {
            // All of our main screen composables can go here
            // LoginScreen()
            // SignupScreen()
            DorDScreen()
            //DiscipleQuestions()
            //WelcomeScreen()
        }
    }
}