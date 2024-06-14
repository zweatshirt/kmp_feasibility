import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import home.disciple_home.DiscipleHomeScreen
import discipleship.WelcomeScreen
import calendar.calendarPage
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import acct_creation.LoginScreen
import acct_creation.SignupScreen
import cafe.adriel.voyager.navigator.Navigator
import discipler.introQuestions.DisciplerQuestions
import discipleship.DorDScreen
import home.discipler_home.DisciplerHomeScreen

@Composable
@Preview
// At some point soon we need to define navigation
fun App() {
    MaterialTheme {
        // All of our main screen composables can go here
        WelcomeScreen()
        //LoginScreen()
        //SignupScreen()
        // DorDScreen()
        // DiscipleQuestions()
        // DisciplerQuestions()
        //HomeScreen()
        //calendarPage()
    }
}