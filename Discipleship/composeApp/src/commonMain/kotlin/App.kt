import acct_creation.presentation.ui.WelcomeScreen
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.navigator.Navigator
import global_consts.Constants
import org.jetbrains.compose.ui.tooling.preview.Preview
import io.realm.kotlin.mongodb.App


@OptIn(ExperimentalVoyagerApi::class)
@Composable
@Preview
fun App() {

    // Go to home screen if user is logged in
    // In the future when the user logic is more defined we need to
    // take them to either the DiscipleHomeScreen or DisciplerHomeScreen
    // based on user type.
    // idk how necessary the welcome screen is
    // but preferably we can find a way for recurring users
    // to go directly to login page instead.
    // We would have to cache something saying they've used
    // our app.
    Navigator(WelcomeScreen())
//    Navigator(
//        screen = WelcomeScreen(),
//        disposeBehavior = NavigatorDisposeBehavior(disposeSteps = false)
//    ) { navigator ->
//        SlideTransition(navigator,
//            disposeScreenAfterTransitionEnd = true
//        )
//    }
}