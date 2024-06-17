import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview
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
    }
}