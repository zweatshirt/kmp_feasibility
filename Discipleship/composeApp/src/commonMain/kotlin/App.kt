import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview
import home.disciple_home.DiscipleHomeScreen
import discipleship.WelcomeScreen
import calendar.calendarPage
import acct_creation.LoginScreen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import discipleship.WelcomeScreen


@Composable
@Preview
// At some point soon we need to define navigation
fun App() {
    Navigator(WelcomeScreen()) {navigator ->
        SlideTransition(navigator)
    MaterialTheme {
        Scaffold {
            // All of our main screen composables can go here
            //WelcomeScreen()
            //LoginScreen()
            //SignupScreen()
            // DorDScreen()
            // DiscipleQuestions()
            // DisciplerQuestions()
            //HomeScreen()
            calendarPage()
        }
    }
}