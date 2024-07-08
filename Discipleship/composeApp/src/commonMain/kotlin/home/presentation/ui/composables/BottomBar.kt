package home.presentation.ui.composables

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
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import calendar.CalendarPage
import discipleship.composeapp.generated.resources.Res
import discipleship.composeapp.generated.resources.social
import home.presentation.ui.disciple_home.DiscipleHomeScreen
import home.presentation.ui.discipler_home.DisciplerHomeScreen
import screenmodel.ScreenData
import org.jetbrains.compose.resources.painterResource
import profile.presentation.AccountProfile
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
            onClick = {
                val screens = mutableListOf<Screen>()
                if (screenData.isDisciple) {
                    screens.add(DiscipleHomeScreen(screenData))
                } else {
                    screens.add(DisciplerHomeScreen(screenData))
                }
                navigator.items.forEach {
                    if (it.key != "Profile") {
                        screens.add(it)
                    }
                }
                screens.add(AccountProfile(screenData))
                navigator.replaceAll(screens)
            },
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
                        val screens = mutableListOf<Screen>()
                        screens.add(DiscipleHomeScreen(screenData))
                        navigator.items.forEach {
                            if (it.key != "DiscipleHome") {
                                screens.add(it)
                            }
                        }
                        screens.add(DiscipleHomeScreen(screenData))
                        navigator.replaceAll(screens)
                    } else {
                        val screens = mutableListOf<Screen>()
                        screens.add(DisciplerHomeScreen(screenData))
                        navigator.items.forEach {
                            if (it.key != "DisciplerHome") {
                                screens.add(it)
                            }
                        }
                        screens.add(DisciplerHomeScreen(screenData))
                        navigator.replaceAll(screens)
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
                    val screens = mutableListOf<Screen>()
                    if (screenData.isDisciple) {
                        screens.add(DiscipleHomeScreen(screenData))
                    } else {
                        screens.add(DisciplerHomeScreen(screenData))
                    }
                    navigator.items.forEach {
                        if (it.key != "Calendar") {
                            screens.add(it)
                        }
                    }
                    screens.add(CalendarPage(screenData))
                    navigator.replaceAll(screens)
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