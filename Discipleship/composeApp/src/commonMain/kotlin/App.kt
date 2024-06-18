import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth
import discipleship.WelcomeScreen
import home.disciple_home.DiscipleHomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    val scope = rememberCoroutineScope()
    val auth = { Firebase.auth } // for authorizing users
    var firebaseUser: FirebaseUser? by remember {
        mutableStateOf(null)
    }
    var userEmail by remember { mutableStateOf("") }
    val userPassword by remember { mutableStateOf("") }

    // Go to home screen if user is logged in
    // In the future when the user logic is more defined we need to
    // take them to either the DiscipleHomeScreen or DisciplerHomeScreen
    // based on user type.
    if (firebaseUser != null) {
        Navigator(DiscipleHomeScreen()) {navigator ->
            SlideTransition(navigator)
        }
    }
    else { // idk how necessary the welcome screen is
        // but preferably we can find a way for recurring users
        // to go directly to login page instead.
        // We would have to cache something saying they've used
        // our app.
        Navigator(WelcomeScreen()) {navigator ->
            SlideTransition(navigator)
        }
    }
}