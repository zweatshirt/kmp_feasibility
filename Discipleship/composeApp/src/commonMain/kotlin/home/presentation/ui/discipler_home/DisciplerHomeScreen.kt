package home.presentation.ui.discipler_home

import profile.domain.model.Disciple
import calendar.domain.model.Meeting
import home.domain.model.Tool
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import discipleship.composeapp.generated.resources.Res
import discipleship.composeapp.generated.resources.avatar
import home.presentation.ui.composables.BottomBar
import home.presentation.ui.composables.DiscipleSection
import home.presentation.ui.composables.MeetingSection
import home.presentation.ui.composables.ToolsSection
import home.presentation.ui.composables.TopBar
import viewmodel.ScreenData
import ui.theme.backgroundLight

// Will eventually be dynamically loaded
val disciplesList = mutableListOf(
    Disciple(
        "2",
        firstName = "Zach",
        lastName = "Linscott",
        email = "zach7307@gmail.com",
        image = Res.drawable.avatar,
        bio = "I like food"
    ),
    Disciple(
        "2",
        firstName = "Josh",
        lastName = "Ward",
        email = "idk",
        image = null,
        bio = "I like food too"
    ),
    Disciple(
        "2",
        firstName = "Meep",
        lastName = "Moop",
        email = "meepmoop",
        image = null,
        bio = "I like food"
    ),
    Disciple(
        "2",
        firstName = "Z",
        lastName = "L",
        email = "Zach2",
        image = null,
        bio = "I like food"
    )
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
        "100",
        name = "Four Spiritual Laws",
        description = "This is a cool tool.",
        toolLink = "https://knowgod.com/en/fourlaws/0",
        totalViews = 1000000
    )
)

data class DisciplerHomeScreen(val screenData: ScreenData): Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Scaffold(
            topBar = {
                TopBar(navigator = navigator, title = "Christ Companions")
            },
            bottomBar = {  // will refactor
                BottomBar(navigator = navigator, currentScreen = "Home", screenData = screenData)
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

