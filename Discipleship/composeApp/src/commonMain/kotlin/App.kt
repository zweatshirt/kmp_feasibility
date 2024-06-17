import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import discipleship.WelcomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
// At some point soon we need to define navigation
fun App() {
    Navigator(WelcomeScreen()) {navigator ->
        SlideTransition(navigator)
    }
}