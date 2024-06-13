import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.*
import calendar.calendarPage
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview
import discipleship.WelcomeScreen // Keep to use for testing welcome Screen
import disciple.introQuestions.DiscipleQuestions // keep to use for testing disciple Questions
import discipler.introQuestions.DisciplerQuestions // keep to use for testing discipler Questions
import ui.theme.primaryContainerLight
import ui.theme.secondaryLight

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
            WelcomeScreen()
            //LoginScreen()
            //SignupScreen()
            // DorDScreen()
            // DiscipleQuestions()
            // DisciplerQuestions()
            //HomeScreen()
        }
    }
}