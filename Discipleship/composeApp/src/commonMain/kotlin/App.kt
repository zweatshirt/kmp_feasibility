import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview
import calendarV2.CalendarV2

@Composable
@Preview


// At some point soon we need to define navigation
fun App() {
    MaterialTheme {
        Scaffold(
//            bottomBar = { // will refactor
//                NavigationBar(
//                    modifier = Modifier.navigationBarsPadding(), containerColor = primaryContainerLight,
//                    contentColor = secondaryLight) {
//                    NavigationBarItem(
//                        selected = true,
//                        onClick = {},
//                        icon = {
//                            Icon(imageVector = Icons.Rounded.Person, contentDescription = "Social")
//                        },
//                        colors = NavigationBarItemDefaults.colors(secondaryLight)
//                    )
//                    NavigationBarItem(
//                        selected = true,
//                        onClick = {},
//                        icon = {
//                            Icon(imageVector = Icons.Rounded.Home, contentDescription = "Social")
//                        },
//                        colors = NavigationBarItemDefaults.colors(secondaryLight)
//                    )
//                    NavigationBarItem(
//                        selected = true,
//                        onClick = {},
//                        icon = {
//                            Icon(imageVector = Icons.Rounded.DateRange, contentDescription = "Social")
//                        },
//                        colors = NavigationBarItemDefaults.colors(secondaryLight)
//                    )
//                }
//            }
        ) {
            // All of our main screen composables can go here
            //WelcomeScreen()
            //LoginScreen()
            //SignupScreen()
            // DorDScreen()
            // DiscipleQuestions()
            // DisciplerQuestions()
            //HomeScreen()
            //calendarPage()
            CalendarV2()
        }
    }
}