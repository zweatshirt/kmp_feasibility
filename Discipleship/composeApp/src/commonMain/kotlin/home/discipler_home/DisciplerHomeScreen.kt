package home.discipler_home

import user.Disciple
import meetings.Meeting
import tools.Tool
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import calendar.CalendarPage
import discipleship.composeapp.generated.resources.Res
import discipleship.composeapp.generated.resources.avatar
import discipleship.composeapp.generated.resources.social
import meetings.MeetingSection
import tools.ToolsSection
import org.jetbrains.compose.resources.painterResource
import ui.theme.backgroundLight
import ui.theme.inversePrimaryLight
import ui.theme.primaryLight
import ui.theme.secondaryLight

// Will eventually be dynamically loaded
val disciplesList = mutableListOf(
    Disciple(
        firstName = "Zach",
        lastName = "Linscott",
        email = "zach7307@gmail.com",
        image = Res.drawable.avatar,
        bio = "I like food"
    ),
    Disciple(
        firstName = "Josh",
        lastName = "Ward",
        email = "idk",
        image = null,
        bio = "I like food too"
    ),
    Disciple(
        firstName = "Meep",
        lastName = "Moop",
        email = "meepmoop",
        image = null,
        bio = "I like food"
    ),
    Disciple(
        firstName = "Z",
        lastName = "L",
        email = "Zach2",
        image = null,
        bio = "I like food"
    ),
    )

// will eventually be populated dynamically
val meetingsList = mutableListOf(
    Meeting(
        date = "7/7/2024",
        time = "10:30AM",
        disciple = disciplesList[2],
        note = "Meep mooping"
    )
)

// will eventually be populated dynamically
val toolsList = mutableListOf(
    Tool(
        toolName = "Four Spiritual Laws",
        toolDescription = "This is a cool tool.",
        toolLink = "https://knowgod.com/en/fourlaws/0",
        languageList = listOf("English", "German")
    )
)

class DisciplerHomeScreen: Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    navigationIcon = {
                        IconButton(onClick = { /* do something */ }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = "Localized description",
                            )
                        }
                    },
                    title = {
                        Text(
                            text = "Christ Companions",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = primaryLight
                        ) // change in the future to show current screen name
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = inversePrimaryLight,
                        titleContentColor = primaryLight
                    ),
                    actions = {
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Outlined.Info,
                                contentDescription = "App info"
                            )
                        }
                    }
                )
            },
            bottomBar = {  // will refactor
                NavigationBar(
                    modifier = Modifier
                        .height(65.dp)
                        .navigationBarsPadding(),
                    containerColor = inversePrimaryLight,
                    contentColor = secondaryLight
                ) {
                    NavigationBarItem(
                        selected = true,
                        onClick = {},
                        icon = {
                            Icon(
                                modifier = Modifier.size(25.dp),
                                painter = painterResource(Res.drawable.social),
                                contentDescription = "Social",
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = inversePrimaryLight,
                            selectedIconColor = primaryLight
                        )
                    )
                    NavigationBarItem(
                        selected = true,
                        onClick = {},
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
                        onClick = {navigator.push(CalendarPage())},
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
        ) { padding ->
            Column(
                modifier = Modifier
                    .background(backgroundLight)
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
            ) {
                MeetingSection()
                DiscipleSection()
                ToolsSection()
            }
        }
    }
}

