import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview
import home.disciple_home.DiscipleHomeScreen
import discipleship.WelcomeScreen
import acct_creation.LoginScreen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition


@Composable
@Preview
// At some point soon we need to define navigation
fun App() {
    Navigator(WelcomeScreen()) {navigator ->
        SlideTransition(navigator)
    }
}