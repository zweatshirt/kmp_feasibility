package home

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import calendar.CalendarPage
import discipleship.composeapp.generated.resources.Res
import discipleship.composeapp.generated.resources.social
import home.disciple_home.DiscipleHomeScreen
import home.discipler_home.DisciplerHomeScreen
import navigation.ScreenData
import org.jetbrains.compose.resources.painterResource
import profile.AccountProfile
import ui.theme.inversePrimaryLight
import ui.theme.primaryLight
import ui.theme.secondaryLight

@Composable
fun BottomBar(navigator: Navigator, currentScreen: String, screenData: ScreenData) {
    NavigationBar(
        modifier = Modifier
            .height(65.dp)
            .navigationBarsPadding(),
        containerColor = inversePrimaryLight,
        contentColor = secondaryLight
    ) {
        NavigationBarItem(
            selected = true,
            onClick = {navigator.push(AccountProfile(screenData))},
            icon = {
                Icon(
                    modifier = Modifier.size(25.dp),
                    painter = painterResource(Res.drawable.social),
                    contentDescription = "Profile",
                )
            },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = inversePrimaryLight,
                selectedIconColor = primaryLight
            )
        )
        NavigationBarItem(
            selected = true,
            onClick = {
                if (currentScreen != "Home") {
                    if (screenData.isDisciple) {
                        navigator.push(DiscipleHomeScreen(screenData))
                    } else {
                        navigator.push(DisciplerHomeScreen(screenData))
                    }
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Rounded.Home,
                    contentDescription = "Home"
                )
            },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = inversePrimaryLight,
                selectedIconColor = primaryLight
            )
        )
        NavigationBarItem(
            selected = true,
            onClick = {
                if (currentScreen != "Calendar") {
                    navigator.push(CalendarPage(screenData))
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Rounded.DateRange,
                    contentDescription = "Calendar"
                )
            },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = inversePrimaryLight,
                selectedIconColor = primaryLight
            )
        )
    }
}